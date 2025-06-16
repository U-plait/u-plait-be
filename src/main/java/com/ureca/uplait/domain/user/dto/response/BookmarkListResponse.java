package com.ureca.uplait.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "즐겨찾기 전체 조회 반환 DTO")
public class BookmarkListResponse {
    @Schema(description = "즐겨찾기 목록", example = "즐겨찾기 목록")
    private List<BookmarkResponse> bookmarkList;
    @Schema(description = "다음 페이지 유무", example = "false")
    private boolean hasNext;
}
