package com.ureca.uplait.domain.auth.dto;

import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Schema(description="카카오 로그인 요청")
public class KakaoAuthRequest {
	@Schema(description="카카오 인가 코드", example="abc123xyz")
	private String code;
}
