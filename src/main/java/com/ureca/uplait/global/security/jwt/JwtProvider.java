package com.ureca.uplait.global.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ureca.uplait.domain.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token-validity}")
	private long ACCESS_TOKEN_VALIDITY_SECONDS;

	@Value("${jwt.refresh-token-validity}")
	private long REFRESH_TOKEN_VALIDITY_SECONDS;

	public String createAccessToken(User user) {
		return Jwts.builder()
			.setSubject(String.valueOf(user.getId()))
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
			.signWith(SignatureAlgorithm.HS512, secret.getBytes())
			.compact();
	}

	public String createRefreshToken(User user) {
		return Jwts.builder()
			.setSubject(String.valueOf(user.getId()))
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS))
			.signWith(SignatureAlgorithm.HS256, secret.getBytes())
			.compact();
	}

	public void addAccessTokenCookie(HttpServletResponse response, String token){
		Cookie cookie = new Cookie("accessToken", token);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge((int) (ACCESS_TOKEN_VALIDITY_SECONDS / 1000));
		response.addCookie(cookie);
	}

	public void addRefreshTokenCookie(HttpServletResponse response, String token){
		Cookie cookie = new Cookie("refreshToken", token);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		cookie.setMaxAge((int) (REFRESH_TOKEN_VALIDITY_SECONDS / 1000));
		response.addCookie(cookie);
	}

	public void deleteAccessTokenCookie(HttpServletResponse response){
		Cookie cookie = new Cookie("accessToken", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	public void deleteRefreshTokenCookie(HttpServletResponse response){
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	public LocalDateTime getRefreshTokenExpiry(String token){
		Claims claims = Jwts.parser()
			.setSigningKey(secret.getBytes())
			.parseClaimsJws(token)
			.getBody();

		return LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
	}

}
