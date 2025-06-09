package com.ureca.uplait.domain.review.service;

import com.ureca.uplait.domain.review.dto.response.ReviewDetailListResponse;
import com.ureca.uplait.domain.review.dto.response.ReviewDetailResponse;
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
    public ReviewDetailListResponse getReviewList(User user, int size, Long lastReviewId, Long planId) {
        List<ReviewDetailResponse> reviewDetailList = new java.util.ArrayList<>(reviewRepository.getReviewsByPlanAndPage(size + 1, lastReviewId, planId)
            .stream().map(r -> new ReviewDetailResponse(r, r.getUser().getId() == user.getId()))
            .toList());

        // 다음 페이지 확인 및 반환값 조정
        boolean hasNext = (reviewDetailList.size() > size);
        if (hasNext) reviewDetailList.remove(reviewDetailList.size() - 1);

        return new ReviewDetailListResponse(reviewDetailList, hasNext);
    }
}
