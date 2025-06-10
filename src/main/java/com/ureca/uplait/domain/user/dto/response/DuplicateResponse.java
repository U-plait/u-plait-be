package com.ureca.uplait.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "중복 여부 담는 DTO")
public class DuplicateResponse {

	@Schema(description = "중복 여부", example = "true")
	boolean duplicated;

	public DuplicateResponse(boolean duplicated) {
		this.duplicated = duplicated;
	}
}
