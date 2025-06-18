package com.ureca.uplait.domain.admin.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "리뷰 조회 상세")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDetailReviewResponse {

    @Schema(description = "리뷰 id", example = "1")
    private Long reviewId;

    @Schema(description = "사용자 이름", example = "유플레")
    private String userName;

    @Schema(description = "리뷰 제목", example = "최고의 요금제")
    private String title;

    @Schema(description = "별점", example = "5")
    private int rating;

    @Schema(description = "작성 날짜", example = "25.06.08")
    private LocalDateTime createdAt;

    @Schema(description = "작성 내용", example = "멋지다")
    private String content;

}