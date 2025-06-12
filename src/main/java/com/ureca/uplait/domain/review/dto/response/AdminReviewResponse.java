package com.ureca.uplait.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;

@Getter
@Schema(description = "리뷰 조회 상세")
public class AdminReviewResponse {

    @Schema(description = "리뷰 id", example = "1")
    private Long reviewId;

    @Schema(description = "사용자 이름", example = "유플레")
    private String userName;

    @Schema(description = "리뷰 제목", example = "최고의 요금제")
    private String title;

    @Schema(description = "별점", example = "5")
    private int rating;

    @Schema(description = "작성 날짜", example = "25.06.08")
    private String createdAt;

    public AdminReviewResponse(Long reviewId, String userName, String title, int rating,
        LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.title = title;
        this.rating = rating;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
    }
}
