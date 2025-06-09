package com.ureca.uplait.domain.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ureca.uplait.domain.review.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class CustomReviewRepositoryImpl implements CustomReviewRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Review> getReviewsByPlanAndPage(int size, Long lastReviewId, Long planId) {
        return jpaQueryFactory
            .selectFrom(review)
            .where(
                ltReviewId(lastReviewId),
                planIdEq(planId)
            )
            .orderBy(review.id.desc())
            .limit(size)
            .fetch();
    }

    private BooleanExpression ltReviewId(Long lastReviewId) {
        if(lastReviewId == null) return null;
        return review.id.lt(lastReviewId);
    }

    private BooleanExpression planIdEq(Long planId) {
        if(planId == null) return null;
        return review.plan.id.eq(planId);
    }
}
