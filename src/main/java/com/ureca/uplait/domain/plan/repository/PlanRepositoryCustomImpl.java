package com.ureca.uplait.domain.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.entity.QIPTVPlan;
import com.ureca.uplait.domain.plan.entity.QInternetPlan;
import com.ureca.uplait.domain.plan.entity.QMobilePlan;
import com.ureca.uplait.domain.user.entity.QBookmark;
import com.ureca.uplait.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PlanRepositoryCustomImpl implements PlanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MobilePlanDetailResponse> findAllMobilePlans(Pageable pageable, User user) {
        QMobilePlan mobilePlan = QMobilePlan.mobilePlan;
        QBookmark bookmark = QBookmark.bookmark;

        List<Long> bookmarkIdList;
        if (user != null) {
            bookmarkIdList = queryFactory
                .select(bookmark.plan.id)
                .from(bookmark)
                .where(bookmark.user.id.eq(user.getId()))
                .fetch();
        } else {
            bookmarkIdList = Collections.emptyList();
        }

        List<MobilePlanDetailResponse> content = queryFactory
            .selectFrom(mobilePlan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(mobilePlan.id.asc())
            .fetch()
            .stream()
            .map(mp -> new MobilePlanDetailResponse(bookmarkIdList.contains(mp.getId()), mp))
            .toList();

        return PageableExecutionUtils.getPage(content, pageable, () ->
            Optional.ofNullable(
                queryFactory.select(mobilePlan.count())
                    .from(mobilePlan)
                    .fetchOne()
            ).orElse(0L)
        );
    }

    @Override
    public Page<InternetPlanDetailResponse> findAllInternetPlans(Pageable pageable, User user) {
        QInternetPlan internetPlan = QInternetPlan.internetPlan;
        QBookmark bookmark = QBookmark.bookmark;

        List<Long> bookmarkIdList;
        if (user != null) {
            bookmarkIdList = queryFactory
                .select(bookmark.plan.id)
                .from(bookmark)
                .where(bookmark.user.id.eq(user.getId()))
                .fetch();
        } else {
            bookmarkIdList = Collections.emptyList();
        }

        List<InternetPlanDetailResponse> content = queryFactory
            .selectFrom(internetPlan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(internetPlan.id.asc())
            .fetch()
            .stream()
            .map(ip -> new InternetPlanDetailResponse(bookmarkIdList.contains(ip.getId()), ip))
            .toList();

        return PageableExecutionUtils.getPage(content, pageable, () ->
            Optional.ofNullable(
                queryFactory.select(internetPlan.count())
                    .from(internetPlan)
                    .fetchOne()
            ).orElse(0L)
        );
    }

    @Override
    public Page<IPTVPlanDetailResponse> findAllIPTVPlans(Pageable pageable, User user) {
        QIPTVPlan iptvPlan = QIPTVPlan.iPTVPlan;
        QBookmark bookmark = QBookmark.bookmark;

        List<Long> bookmarkIdList;
        if (user != null) {
            bookmarkIdList = queryFactory
                .select(bookmark.plan.id)
                .from(bookmark)
                .where(bookmark.user.id.eq(user.getId()))
                .fetch();
        } else {
            bookmarkIdList = Collections.emptyList();
        }

        List<IPTVPlanDetailResponse> content = queryFactory
            .selectFrom(iptvPlan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(iptvPlan.id.asc())
            .fetch()
            .stream()
            .map(ip -> new IPTVPlanDetailResponse(bookmarkIdList.contains(ip.getId()), ip))
            .toList();

        return PageableExecutionUtils.getPage(content, pageable, () ->
            Optional.ofNullable(
                queryFactory.select(iptvPlan.count())
                    .from(iptvPlan)
                    .fetchOne()
            ).orElse(0L)
        );
    }
}
