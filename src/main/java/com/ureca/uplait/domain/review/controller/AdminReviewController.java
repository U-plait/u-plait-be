package com.ureca.uplait.domain.review.controller;

import static org.springframework.data.domain.Sort.Direction;

import com.ureca.uplait.domain.review.dto.response.AdminReviewResponse;
import com.ureca.uplait.domain.review.service.AdminReviewService;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/review")
@RequiredArgsConstructor
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    @GetMapping
    @Operation(summary = "관리자 리뷰 전체 조회", description = "관리자가 전체 리뷰를 페이지 단위로 조회합니다.")
    public CommonResponse<Page<AdminReviewResponse>> getAllReviews(
        @PageableDefault(size = 20, sort = "reviewId", direction = Direction.DESC)
        Pageable pageable
    ) {
        return CommonResponse.success(adminReviewService.getAllReviewsForAdmin(pageable));
    }


    @Operation(summary = "리뷰 삭제 (ID 기반)", description = "리뷰 ID를 기준으로 리뷰를 삭제합니다.")
    @DeleteMapping("/{reviewId}")
    public CommonResponse<Long> deleteReviewById(
        @Parameter(description = "삭제할 리뷰 ID", example = "1", required = true)
        @PathVariable Long reviewId
    ) {
        Long deletedId = adminReviewService.deleteReviewById(reviewId);
        return CommonResponse.success(ResultCode.REVIEW_DELETE_SUCCESS, deletedId);
    }
}
