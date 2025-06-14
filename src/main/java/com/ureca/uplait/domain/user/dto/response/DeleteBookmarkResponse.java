package com.ureca.uplait.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "즐겨찾기 삭제 반환 DTO")
public class DeleteBookmarkResponse {
    private Long planId;
}
