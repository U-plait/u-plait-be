package com.ureca.uplait.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

	@Value("${fastapi.baseUrl}")
	private String fastApiUrl;

	@Bean
	public WebClient kakaoAuthWebClient(){
		return WebClient.builder()
			.baseUrl("https://kauth.kakao.com")
			.defaultHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
			.build();
	}

	@Bean
	public WebClient kakaoApiWebClient(){
		return WebClient.builder()
			.baseUrl("https://kapi.kakao.com")
			.defaultHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
			.build();
	}

	@Bean
	public WebClient fastApiWebClient() {
		return WebClient.builder()
			.baseUrl(fastApiUrl)
			.build();
	}
}
