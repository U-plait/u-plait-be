package com.ureca.uplait.domain.review.controller;

import com.ureca.uplait.domain.review.dto.response.ReviewDetailListResponse;
import com.ureca.uplait.domain.review.service.ReviewService;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 요금제별 리뷰 전체 조회
     *
     * @param user 사용자 정보
     * @param size 페이지 크기
     * @param lastReviewId 마지막 리뷰 ID
     * @param planId 요금제 ID
     */
    @Operation(summary = "요금제별 리뷰 전체 조회", description = "요금제별 리뷰 전체 조회")
    @GetMapping("/")
    public CommonResponse<ReviewDetailListResponse> getReviewList(
        @Parameter(description = "사용자정보", required = true)
        @AuthenticationPrincipal User user,
        @Parameter(description = "한 번에 가져올 크기")
        @RequestParam(defaultValue = "5") int size,
        @Parameter(description = "마지막 리뷰 Id")
        @RequestParam(required = false) Long lastReviewId,
        @Parameter(description = "요금제 id")
        @RequestParam Long planId
    ) {
        return CommonResponse.success(reviewService.getReviewList(user, size, lastReviewId, planId));
    }
}
