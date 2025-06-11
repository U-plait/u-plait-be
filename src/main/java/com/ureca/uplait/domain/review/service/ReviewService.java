package com.ureca.uplait.domain.review.service;

import com.ureca.uplait.domain.review.dto.request.ReviewCreateRequest;
import com.ureca.uplait.domain.review.dto.response.ReviewCreateResponse;
import com.ureca.uplait.domain.review.dto.response.ReviewListResponse;
import com.ureca.uplait.domain.review.dto.response.ReviewResponse;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

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
    public ReviewCreateResponse writeReview(User user, ReviewCreateRequest request) {
        Review review = new Review().builder().build();


        Review savedReview = reviewRepository.save(review);
        return new ReviewCreateResponse();
    }
}
