package com.ureca.uplait.domain.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanListResponse;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.entity.QIPTVPlan;
import com.ureca.uplait.domain.plan.entity.QInternetPlan;
import com.ureca.uplait.domain.plan.entity.QMobilePlan;
import com.ureca.uplait.domain.plan.entity.QPlan;
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

    @Override
    public List<PlanListResponse> findAllMobileByList() { // 반환 타입 및 파라미터 변경
        QMobilePlan mobilePlan = QMobilePlan.mobilePlan;

        return queryFactory
            .selectFrom(mobilePlan)
            .orderBy(mobilePlan.id.desc())
            .fetch()
            .stream()
            .map(plan -> new PlanListResponse((Plan) plan))
            .toList();

    }

    @Override
    public List<PlanListResponse> findAllInternetByList() { // 반환 타입 및 파라미터 변경
        QInternetPlan internetPlan = QInternetPlan.internetPlan;

        return queryFactory
            .selectFrom(internetPlan)
            .orderBy(internetPlan.id.desc())
            .fetch()
            .stream()
            .map(plan -> new PlanListResponse((Plan) plan))
            .toList();
    }

    @Override
    public List<PlanListResponse> findAllIPTVByList() {
        QIPTVPlan iptvPlan = QIPTVPlan.iPTVPlan;

        return queryFactory
            .selectFrom(iptvPlan)
            .orderBy(iptvPlan.id.desc())
            .fetch()
            .stream()
            .map(plan -> new PlanListResponse((Plan) plan))
            .toList();
    }

    @Override
    public List<Plan> findPlansByTypeAndIdIn(Class<? extends Plan> type, List<Long> ids) {
        QPlan plan = QPlan.plan;

        return queryFactory
            .selectFrom(plan)
            .where(plan.instanceOf(type) // Class 객체를 직접 전달
                .and(plan.id.in(ids)))
            .fetch();
    }
}
