package com.ureca.uplait.domain.review.repository;

import com.ureca.uplait.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, CustomReviewRepository {
}
