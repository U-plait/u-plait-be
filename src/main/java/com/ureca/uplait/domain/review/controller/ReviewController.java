package com.ureca.uplait.domain.review.controller;

import com.ureca.uplait.domain.review.dto.request.ReviewCreateRequest;
import com.ureca.uplait.domain.review.dto.request.ReviewUpdateRequest;
import com.ureca.uplait.domain.review.dto.response.ReviewCreateResponse;
import com.ureca.uplait.domain.review.dto.response.ReviewDeleteResponse;
import com.ureca.uplait.domain.review.dto.response.ReviewListResponse;
import com.ureca.uplait.domain.review.dto.response.ReviewUpdateResponse;
import com.ureca.uplait.domain.review.service.ReviewService;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "요금제별 리뷰 전체 조회", description = "요금제별 리뷰 전체 조회")
    @GetMapping("/")
    public CommonResponse<ReviewListResponse> getReviewList(
        @Parameter(description = "사용자정보", required = true)
        @AuthenticationPrincipal User user,
        @Parameter(description = "한 번에 가져올 크기")
        @RequestParam(defaultValue = "5") int size,
        @Parameter(description = "마지막 리뷰 Id")
        @RequestParam(required = false) Long lastReviewId,
        @Parameter(description = "요금제 id")
        @RequestParam Long planId
    ) {
        return CommonResponse.success(
            reviewService.getReviewList(user, size, lastReviewId, planId));
    }

    @Operation(summary = "요금제별 리뷰 작성", description = "요금제별 리뷰 작성")
    @PostMapping("/")
    public CommonResponse<ReviewCreateResponse> createReview(
        @AuthenticationPrincipal User user,
        @RequestBody ReviewCreateRequest request
    ) {
        return CommonResponse.success(reviewService.createReview(user, request));
    }

    @Operation(summary = "요금제별 리뷰 수정", description = "요금제별 리뷰 수정")
    @PutMapping("/update")
    public CommonResponse<ReviewUpdateResponse> updateReview(
        @AuthenticationPrincipal User user,
        @RequestBody ReviewUpdateRequest request
    ) {
        return CommonResponse.success(reviewService.updateReview(user, request));
    }

    @Operation(summary = "요금제별 리뷰 삭제", description = "요금제별 리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    public CommonResponse<ReviewDeleteResponse> deleteReview(
        @AuthenticationPrincipal User user,
        @PathVariable Long reviewId
    ) {
        return CommonResponse.success(ResultCode.REVIEW_DELETE_SUCCESS,
            reviewService.deleteReview(user, reviewId));
    }
}
