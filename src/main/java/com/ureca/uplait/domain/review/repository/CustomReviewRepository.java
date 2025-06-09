package com.ureca.uplait.domain.review.repository;

import com.ureca.uplait.domain.review.entity.Review;

import java.util.List;

public interface CustomReviewRepository {
    List<Review> getReviewsByPlanAndPage(int size, Long lastReviewId, Long planId);
}
