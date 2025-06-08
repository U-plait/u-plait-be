package com.ureca.uplait.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginRes {
	@Schema(description="사용자 역할", example="USER")
	private final String role;

	public LoginRes(String role) {
		this.role = role;
	}
}
