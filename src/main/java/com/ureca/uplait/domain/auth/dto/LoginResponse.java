package com.ureca.uplait.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
	@Schema(description="사용자 역할", example="USER")
	private final String role;

	@Schema(description = "사용자 이름", example="홍길동")
	private final String username;

}
