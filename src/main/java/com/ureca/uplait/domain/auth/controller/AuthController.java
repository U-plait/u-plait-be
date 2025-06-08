package com.ureca.uplait.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ureca.uplait.domain.auth.dto.KakaoAuthReq;
import com.ureca.uplait.domain.auth.dto.LoginRes;
import com.ureca.uplait.domain.auth.service.AuthService;
import com.ureca.uplait.domain.user.entity.User;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoginRes> login(@RequestBody KakaoAuthReq request, HttpServletResponse response){
		User user = authService.handleKakaoLogin(request.getCode(), response);
		return ResponseEntity.ok(new LoginRes(user.getRole().name()));
	}

	@PostMapping("/reissue")
	public ResponseEntity<String> reissue(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response){
		authService.reissue(refreshToken, response);
		return ResponseEntity.ok("token 재발급 완료");
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response){
		authService.logout(refreshToken, response);
		return ResponseEntity.ok("로그아웃 완료");
	}

}
