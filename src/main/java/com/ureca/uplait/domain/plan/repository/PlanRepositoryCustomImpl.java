package com.ureca.uplait.domain.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.entity.QIPTVPlan;
import com.ureca.uplait.domain.plan.entity.QInternetPlan;
import com.ureca.uplait.domain.plan.entity.QMobilePlan;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PlanRepositoryCustomImpl implements PlanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MobilePlanDetailResponse> findAllMobilePlans(Pageable pageable) {
        QMobilePlan mobilePlan = QMobilePlan.mobilePlan;

        List<MobilePlanDetailResponse> content = queryFactory
            .selectFrom(mobilePlan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(mobilePlan.id.desc())
            .fetch()
            .stream()
            .map(MobilePlanDetailResponse::new)
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
    public Page<InternetPlanDetailResponse> findAllInternetPlans(Pageable pageable) {
        QInternetPlan internetPlan = QInternetPlan.internetPlan;

        List<InternetPlanDetailResponse> content = queryFactory
            .selectFrom(internetPlan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(internetPlan.id.desc())
            .fetch()
            .stream()
            .map(InternetPlanDetailResponse::new)
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
    public Page<IPTVPlanDetailResponse> findAllIPTVPlans(Pageable pageable) {
        QIPTVPlan iptvPlan = QIPTVPlan.iPTVPlan;

        List<IPTVPlanDetailResponse> content = queryFactory
            .selectFrom(iptvPlan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(iptvPlan.id.desc())
            .fetch()
            .stream()
            .map(IPTVPlanDetailResponse::new)
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
