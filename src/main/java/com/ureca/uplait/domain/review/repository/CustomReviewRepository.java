package com.ureca.uplait.domain.review.repository;

import com.ureca.uplait.domain.admin.dto.response.AdminReviewResponse;
import com.ureca.uplait.domain.review.entity.Review;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomReviewRepository {

    List<Review> getReviewsByPlanAndPage(int size, Long lastReviewId, Long planId);

    Page<AdminReviewResponse> findAllReviewsForAdmin(Pageable pageable);
}
