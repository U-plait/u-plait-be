package com.ureca.uplait.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description="회원가입 시 태그 정보 담는 DTO")
public class AddTagRequest {
	@Schema(description = "선택한 태그 아이디 목록", example = "<1, 3, 4>")
	List<Long> tagIds;
}
