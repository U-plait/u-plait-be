package com.ureca.uplait.domain.review.repository;

import static com.ureca.uplait.domain.review.entity.QReview.review;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.review.dto.response.AdminReviewResponse;
import com.ureca.uplait.domain.review.entity.QReview;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.user.entity.QUser;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomReviewRepositoryImpl implements CustomReviewRepository {

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
        if (lastReviewId == null) {
            return null;
        }
        return review.id.lt(lastReviewId);
    }

    private BooleanExpression planIdEq(Long planId) {
        if (planId == null) {
            return null;
        }
        return review.plan.id.eq(planId);
    }

    @Override
    public Page<AdminReviewResponse> findAllReviewsForAdmin(Pageable pageable) {
        QReview review = QReview.review;
        QUser user = QUser.user;

        List<AdminReviewResponse> content = jpaQueryFactory
            .select(Projections.constructor(
                AdminReviewResponse.class,
                review.id,
                user.name,
                review.title,
                review.rating,
                review.createdAt // 여기선 LocalDateTime 그대로 넘김
            ))
            .from(review)
            .leftJoin(review.user, user)
            .orderBy(review.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return PageableExecutionUtils.getPage(content, pageable, () ->
            Optional.ofNullable(
                jpaQueryFactory.select(review.count())
                    .from(review)
                    .fetchOne()
            ).orElse(0L)
        );
    }

}
