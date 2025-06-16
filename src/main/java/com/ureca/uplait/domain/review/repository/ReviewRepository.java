package com.ureca.uplait.domain.review.repository;

import com.ureca.uplait.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, CustomReviewRepository {
    @Query("SELECT r FROM Review r JOIN FETCH r.plan WHERE r.user.id = :userId")
    List<Review> findByUserIdWithPlan(@Param("userId") Long userId);
}
