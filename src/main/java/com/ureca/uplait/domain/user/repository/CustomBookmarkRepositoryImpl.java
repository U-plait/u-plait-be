package com.ureca.uplait.domain.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.PlanType;
import com.ureca.uplait.domain.user.entity.Bookmark;
import com.ureca.uplait.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ureca.uplait.domain.plan.entity.QPlan.plan;
import static com.ureca.uplait.domain.user.entity.QBookmark.bookmark;
import static com.ureca.uplait.global.response.ResultCode.INVALID_PLAN;

@Repository
@RequiredArgsConstructor
public class CustomBookmarkRepositoryImpl implements CustomBookmarkRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Bookmark> getBookmarksByPlanAndPage(Long userId, int size, Long lastBookmarkId, PlanType planType) {
        return jpaQueryFactory
            .selectFrom(bookmark)
            .join(bookmark.plan, plan).fetchJoin()
            .where(
                ltBookmarkId(lastBookmarkId),
                planTypeEq(planType),
                bookmark.user.id.eq(userId),
                bookmark.plan.availability.eq(true)
            )
            .orderBy(bookmark.id.desc())
            .limit(size)
            .fetch();
    }

    private BooleanExpression ltBookmarkId(Long lastBookmarkId) {
        if(lastBookmarkId == null) return null;
        return bookmark.id.lt(lastBookmarkId);
    }

    private BooleanExpression planTypeEq(PlanType planType) {
        if(planType == PlanType.MOBILE) {
            return bookmark.plan.instanceOf(MobilePlan.class);
        } else if(planType == PlanType.INTERNET) {
            return bookmark.plan.instanceOf(InternetPlan.class);
        } else if(planType == PlanType.IPTV) {
            return bookmark.plan.instanceOf(IPTVPlan.class);
        }
        throw new GlobalException(INVALID_PLAN);
    }
}
