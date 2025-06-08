package com.ureca.uplait.global.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtValidator {

	@Value("${jwt.secret}")
	private String secret;

	public boolean validateToken(String token) {
		try{
			Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public Long getUserIdFromToken(String token){
		Claims claims = Jwts.parser()
			.setSigningKey(secret.getBytes())
			.parseClaimsJws(token)
			.getBody();
		return Long.valueOf(claims.getSubject());
	}

	public String extractAccessTokenFromCookie(HttpServletRequest request) {
		if (request.getCookies() == null) return null;

		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("accessToken")) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
