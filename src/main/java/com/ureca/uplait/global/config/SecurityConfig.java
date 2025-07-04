package com.ureca.uplait.global.config;

import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.security.jwt.JwtValidator;
import com.ureca.uplait.global.security.jwt.entrypoint.JwtAuthenticationEntryPoint;
import com.ureca.uplait.global.security.jwt.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${domain.baseUrl}")
	private String domainBaseUrl;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
			.requestMatchers(
				"/error", "/favicon.ico"
			);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, UserRepository userRepository,
		CorsConfigurationSource corsConfigurationSource, JwtValidator jwtValidator) throws Exception {
		return http
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.formLogin(form -> form.disable())
			.exceptionHandling(e -> e
				.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
			)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/login", "/auth/reissue", "/auth/logout", "/admin/plan/Info").permitAll()
				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
				.requestMatchers("/health").permitAll()
				.requestMatchers("/plan/**").permitAll()
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/user/extra-info").hasRole("TMP_USER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.addFilterBefore(new JwtAuthenticationFilter(jwtValidator, userRepository), UsernamePasswordAuthenticationFilter.class)
			.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of(
			"http://localhost:3000",
			domainBaseUrl
		));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
