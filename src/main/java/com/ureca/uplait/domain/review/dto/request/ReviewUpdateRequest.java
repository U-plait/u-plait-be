package com.ureca.uplait.domain.review.dto.request;

import com.ureca.uplait.domain.review.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "리뷰 수정 요청")
public class ReviewUpdateRequest {
    @Schema(description = "수정할 리뷰 아이디", example = "수정할 리뷰 아이디")
    private Long reviewId;
    @Schema(description = "수정한 리뷰 제목(수정 안하면 기존 값이 들어오게 하기)", example = "최고의 요금제")
    private String title;
    @Schema(description = "수정한 리뷰 내용", example = "가격이 싸고 속도도 빨라요.")
    private String content;
    @Schema(description = "수정한 별점", example = "5")
    private int rating;
}
