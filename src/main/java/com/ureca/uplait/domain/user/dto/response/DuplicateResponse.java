package com.ureca.uplait.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "중복 여부 담는 DTO")
public class DuplicateResponse {

	@Schema(description = "중복 여부", example = "true")
	boolean duplicated;

}
