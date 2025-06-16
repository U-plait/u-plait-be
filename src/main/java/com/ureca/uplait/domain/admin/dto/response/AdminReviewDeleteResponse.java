package com.ureca.uplait.domain.admin.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "관리자 리뷰 삭제 응답")
public class AdminReviewDeleteResponse {
    @Schema(description = "삭제된 reviewId", example = "1")
    Long reviewId;
}
