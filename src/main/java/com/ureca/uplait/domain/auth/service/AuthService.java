package com.ureca.uplait.domain.auth.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ureca.uplait.domain.auth.api.KakaoOauthClient;
import com.ureca.uplait.domain.auth.dto.KakaoUserRes;
import com.ureca.uplait.domain.token.entity.Token;
import com.ureca.uplait.domain.token.repository.TokenRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import com.ureca.uplait.global.security.jwt.JwtProvider;
import com.ureca.uplait.global.security.jwt.JwtValidator;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final JwtProvider jwtProvider;
	private final JwtValidator jwtValidator;
	private final KakaoOauthClient kakaoOauthClient;

	public User handleKakaoLogin(String code, HttpServletResponse response){
		String kakaoAccessToken = kakaoOauthClient.getAccessToken(code);
		KakaoUserRes userInfo = kakaoOauthClient.getUserInfo(kakaoAccessToken);

		String kakaoId = String.valueOf(userInfo.getId());
		String name = userInfo.getKakao_account().getName();

		User user = userRepository.findByKakaoId(kakaoId)
			.orElseGet(() -> userRepository.save(User.createTmpUser(kakaoId, name)));

		String accessToken = jwtProvider.createAccessToken(user);
		String refreshToken = jwtProvider.createRefreshToken(user);
		LocalDateTime expiryTime = jwtProvider.getRefreshTokenExpiry(refreshToken);

		tokenRepository.findByUser(user)
				.ifPresentOrElse(
					token -> token.updateRefreshToken(refreshToken, expiryTime),
					() -> tokenRepository.save(Token.builder()
						.user(user)
						.refreshToken(refreshToken)
						.expiryDate(expiryTime)
						.build())
				);

		jwtProvider.addAccessTokenCookie(response, accessToken);
		jwtProvider.addRefreshTokenCookie(response, refreshToken);

		return user;
	}

	public void reissue(String refreshToken, HttpServletResponse response){
		if (!jwtValidator.validateToken(refreshToken)) {
			throw new GlobalException(ResultCode.INVALID_TOKEN);
		}
		Long userId = jwtValidator.getUserIdFromToken(refreshToken);

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new GlobalException(ResultCode.NOT_FOUND_USER));

		Token token = tokenRepository.findByRefreshToken(refreshToken)
			.orElseThrow(() -> new GlobalException(ResultCode.INVALID_TOKEN));

		String newAccessToken = jwtProvider.createAccessToken(user);
		String newRefreshToken = jwtProvider.createRefreshToken(user);
		LocalDateTime newExpiry = jwtProvider.getRefreshTokenExpiry(newRefreshToken);

		token.updateRefreshToken(newRefreshToken, newExpiry);
		tokenRepository.save(token);

		jwtProvider.addAccessTokenCookie(response, newAccessToken);
		jwtProvider.addRefreshTokenCookie(response, newRefreshToken);
	}

	public void logout(String refreshToken, HttpServletResponse response){

		if (!jwtValidator.validateToken(refreshToken)) {
			throw new GlobalException(ResultCode.INVALID_TOKEN);
		}

		Long userId = jwtValidator.getUserIdFromToken(refreshToken);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new GlobalException(ResultCode.NOT_FOUND_USER));

		tokenRepository.deleteByUser(user);

		jwtProvider.deleteAccessTokenCookie(response);
		jwtProvider.deleteRefreshTokenCookie(response);
	}


}
