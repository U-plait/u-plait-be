package com.ureca.uplait.domain.review.repository;

import com.ureca.uplait.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, CustomReviewRepository {
    Optional<List<Review>> findByUserId(Long userId);
}
