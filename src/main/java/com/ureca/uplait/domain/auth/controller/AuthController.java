package com.ureca.uplait.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ureca.uplait.domain.auth.dto.KakaoAuthRequest;
import com.ureca.uplait.domain.auth.dto.LoginResponse;
import com.ureca.uplait.domain.auth.service.AuthService;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	@Operation(summary = "카카오 로그인", description = "카카오 인가코드를 받아 로그인합니다.")
	public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody KakaoAuthRequest request, HttpServletResponse response){
		User user = authService.handleKakaoLogin(request.getCode(), response);
		LoginResponse result = new LoginResponse(user.getRole().name());
		return ResponseEntity.ok(CommonResponse.success(result));
	}

	@PostMapping("/reissue")
	@Operation(summary = "토큰 재발급", description = "Refresh Token을 통해 새로운 Access Token을 발급합니다.")
	public ResponseEntity<CommonResponse<Void>> reissue(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response){
		authService.reissue(refreshToken, response);
		return ResponseEntity.ok(new CommonResponse<>(ResultCode.REISSUE_SUCCESS));
	}

	@PostMapping("/logout")
	@Operation(summary = "로그아웃", description = "Refresh Token을 만료시키고 로그아웃합니다.")
	public ResponseEntity<CommonResponse<Void>> logout(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response){
		authService.logout(refreshToken, response);
		return ResponseEntity.ok(new CommonResponse<>(ResultCode.LOGOUT_SUCCESS));
	}

}
