package com.ureca.uplait.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "리뷰 작성 응답")
public class ReviewCreateResponse {
    @Schema(description = "리뷰 아이디", example = "1")
    private Long reviewId;
}
