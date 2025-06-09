package com.ureca.uplait.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "리뷰 리스트 조회 결과")
@AllArgsConstructor
public class ReviewListResponse {
    @Schema(description = "리뷰List", example = "리뷰List")
    private List<ReviewResponse> reviewList;
    @Schema(description = "다음 페이지 유무", example = "true")
    private boolean hasNext;
}
