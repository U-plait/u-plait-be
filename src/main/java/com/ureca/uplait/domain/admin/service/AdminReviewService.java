package com.ureca.uplait.domain.admin.service;

import com.ureca.uplait.domain.admin.dto.response.AdminDetailReviewResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminReviewDeleteResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminReviewResponse;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdminReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Page<AdminReviewResponse> getAllReviewsForAdmin(Pageable pageable) {
        return reviewRepository.findAllReviewsForAdmin(pageable);
    }

    @Transactional(readOnly = true)
    public AdminDetailReviewResponse getReviewDetailForAdmin(Long reviewId,
        User currentUser) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new GlobalException(ResultCode.REVIEW_NOT_FOUND));

        String reviewCreatorName;
        if (review.getUser() != null) {
            reviewCreatorName = review.getUser().getName();
        } else {
            reviewCreatorName = "Unknown User";
        }
        return new AdminDetailReviewResponse(
            review.getId(),
            reviewCreatorName,
            review.getTitle(),
            review.getRating(),
            review.getCreatedAt(),
            review.getContent()
        );
    }

    @Transactional
    public AdminReviewDeleteResponse deleteReviewById(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new GlobalException(ResultCode.REVIEW_NOT_FOUND);
        }
        reviewRepository.deleteById(reviewId);
        return new AdminReviewDeleteResponse(reviewId);
    }
}
