package com.ureca.uplait.domain.review.dto.request;

import com.ureca.uplait.domain.plan.entity.Plan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "리뷰 작성 요청")
public class ReviewCreateRequest {
    @Schema(description = "요금제 id", example = "123")
    private Long planId;
    @Schema(description = "리뷰 제목", example = "최고의 요금제")
    private String title;
    @Schema(description = "리뷰 내용", example = "가격이 싸고 속도도 빨라요.")
    private String content;
    @Schema(description = "별점", example = "5")
    private int rating;
}
