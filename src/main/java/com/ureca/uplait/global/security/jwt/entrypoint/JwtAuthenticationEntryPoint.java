package com.ureca.uplait.global.security.jwt.entrypoint;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ureca.uplait.global.security.jwt.JwtProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException, ServletException {
		System.out.println("[EntryPoint] 401 Unauthorized 발생 - URI: " + request.getRequestURI());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
