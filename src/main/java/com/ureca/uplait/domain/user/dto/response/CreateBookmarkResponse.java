package com.ureca.uplait.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "즐겨찾기 추가 반환 DTO")
public class CreateBookmarkResponse {
    private Long bookmarkId;
}
