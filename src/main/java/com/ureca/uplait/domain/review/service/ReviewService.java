package com.ureca.uplait.domain.review.service;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.review.dto.request.ReviewCreateRequest;
import com.ureca.uplait.domain.review.dto.request.ReviewUpdateRequest;
import com.ureca.uplait.domain.review.dto.response.*;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlanRepository planRepository;

    /**
     * 요금제별 리뷰 조회
     */
    @Transactional(readOnly = true)
    public ReviewListResponse getReviewList(User user, int size, Long lastReviewId, Long planId) {
        List<ReviewResponse> reviewDetailList = new java.util.ArrayList<>(reviewRepository.getReviewsByPlanAndPage(size + 1, lastReviewId, planId)
            .stream().map(r -> new ReviewResponse(r, r.getUser().getId().equals(user.getId())))
            .toList());

        // 다음 페이지 확인 및 반환값 조정
        boolean hasNext = (reviewDetailList.size() > size);
        if (hasNext) reviewDetailList.remove(reviewDetailList.size() - 1);

        return new ReviewListResponse(reviewDetailList, hasNext);
    }

    /**
     * 요금제별 리뷰 작성
     */
    @Transactional
    public ReviewCreateResponse createReview(User user, ReviewCreateRequest request) {

        Plan plan = planRepository.findById(request.getPlanId()) .orElseThrow(() -> new GlobalException(ResultCode.PLAN_NOT_FOUND));
        Review review = Review.builder()
                .user(user)
                .plan(plan)
                .title(request.getTitle())
                .content(request.getContent())
                .rating(request.getRating())
                .build();

        Review savedReview = reviewRepository.save(review);

        return new ReviewCreateResponse(savedReview.getId());
    }

    @Transactional
    public ReviewUpdateResponse updateReview(User user, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(request.getReviewId()).get();

        review.updateReview(
                request.getTitle(),
                request.getContent(),
                request.getRating()
        );

        reviewRepository.save(review);
        return new ReviewUpdateResponse(review.getId());
    }

    @Transactional
    public ReviewDeleteResponse deleteReview(User user, Long reviewId) {
        reviewRepository.deleteById(reviewId);
        return new ReviewDeleteResponse(reviewId);
    }
}
