package com.ureca.uplait.domain.review.dto.response;

import com.ureca.uplait.domain.review.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Schema(description = "리뷰 조회 상세")
public class ReviewDetailResponse {
    @Schema(description = "리뷰 id", example = "1")
    private Long reviewId;
    @Schema(description = "사용자 이름", example = "유플레")
    private String userName;
    @Schema(description = "리뷰 작성자 여부", example = "true")
    private boolean isAuthor;
    @Schema(description = "리뷰 제목", example = "최고의 요금제")
    private String title;
    @Schema(description = "리뷰 내용", example = "가격이 싸고 속도도 빨라요.")
    private String content;
    @Schema(description = "별점", example = "5")
    private int rating;
    @Schema(description = "작성 날짜", example = "25.06.08")
    private String createdAt;

    public ReviewDetailResponse(Review review, boolean isAuthor) {
        this.reviewId = review.getId();
        this.userName = review.getUser().getName();
        this.isAuthor = isAuthor;
        this.title = review.getTitle();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.createdAt = review.getCreatedAt().format(DateTimeFormatter.ofPattern("yy.MM.dd"));
    }
}
