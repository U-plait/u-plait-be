package com.ureca.uplait.domain.mypage.dto.response;

import com.ureca.uplait.domain.review.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "내가 쓴 리뷰 응답")
@AllArgsConstructor
public class MyReviewsResponse {
    @Schema(description = "리뷰 id", example = "1")
    private Long reviewId;
    @Schema(description = "요금제 id", example = "1")
    private Long planId;
    @Schema(description = "요금제 이름", example = "5G 요금제")
    private String planName;
    @Schema(description = "리뷰 제목", example = "이 요금제 끝내줘요")
    private String title;
    @Schema(description = "리뷰 내용", example = "이 요금제 끝내줘요 데이터 걱정없이 잘 쓰고 있습니다.")
    private String content;
    @Schema(description = "별점", example = "5")
    private int rating;
    @Schema(description = "작성일", example = "25.06.08")
    private LocalDateTime createdAt;
}
