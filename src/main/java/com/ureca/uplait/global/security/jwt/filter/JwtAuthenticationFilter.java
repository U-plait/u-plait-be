package com.ureca.uplait.global.security.jwt.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.security.jwt.JwtValidator;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtValidator jwtValidator;
	private final UserRepository userRepository;

	public JwtAuthenticationFilter(JwtValidator jwtValidator, UserRepository userRepository) {
		this.jwtValidator = jwtValidator;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String token = jwtValidator.extractAccessTokenFromCookie(request);

		if(token != null && jwtValidator.validateToken(token)){
			Long userId = jwtValidator.getUserIdFromToken(token);
			User user = userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole())));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
}
