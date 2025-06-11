package com.ureca.uplait.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "리뷰 작성 요청")
public class ReviewCreateRequest {
    @Schema(description = "요금제 id", example = "12")
    private Long planId;
    @Schema(description = "리뷰 제목", example = "최고의 요금제")
    private String title;
    @Schema(description = "리뷰 내용", example = "가격이 싸고 속도도 빨라요.")
    private String content;
    @Schema(description = "별점", example = "5")
    private int rating;
}
