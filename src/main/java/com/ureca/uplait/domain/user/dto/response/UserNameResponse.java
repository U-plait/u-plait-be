package com.ureca.uplait.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "네비게이션바에 활용할 사용자 이름 담는 DTO")
public class UserNameResponse {

	@Schema(description="사용자 이름", example="홍길동")
	private String name;

	public UserNameResponse(String name) {this.name = name;}
}
