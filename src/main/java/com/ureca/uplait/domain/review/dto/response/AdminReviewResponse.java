package com.ureca.uplait.domain.review.dto.response;

import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "리뷰 조회 상세")
@NoArgsConstructor
@Builder
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

    @Column(nullable = false)
    private String content;

    public AdminReviewResponse(Long reviewId, String userName, String title, int rating,
        LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.title = title;
        this.rating = rating;
        this.createdAt = createdAt.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
    }

    public AdminReviewResponse(Long reviewId, String userName, String title, int rating,
        String createdAt, String content) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.title = title;
        this.rating = rating;
        this.createdAt = createdAt;
        this.content = content;
    }

    public static AdminReviewResponse from(Review review) {
        if (review == null) {
            throw new GlobalException(ResultCode.REVIEW_NOT_FOUND);
        }

        String userName;
        if (review.getUser() != null) {
            userName = review.getUser().getName();
        } else {
            userName = "Unknown";
        }

        String formattedCreatedAt;
        if (review.getCreatedAt() != null) {
            formattedCreatedAt = review.getCreatedAt()
                .format(DateTimeFormatter.ofPattern("yy.MM.dd"));
        } else {
            formattedCreatedAt = "날짜 정보 없음";
        }

        return AdminReviewResponse.builder()
            .reviewId(review.getId())
            .userName(userName)
            .title(review.getTitle())
            .rating(review.getRating())
            .createdAt(formattedCreatedAt)
            .content(review.getContent())
            .build();
    }
}
