package com.ureca.uplait.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "리뷰 삭제 응답")
public class ReviewDeleteResponse {
    @Schema(description = "삭제한 리뷰 id", example = "1")
    private Long reviewId;
}
