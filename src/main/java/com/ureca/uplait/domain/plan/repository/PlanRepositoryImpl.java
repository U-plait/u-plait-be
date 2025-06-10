package com.ureca.uplait.domain.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanAdminResponse;
import com.ureca.uplait.domain.plan.entity.QIPTVPlan;
import com.ureca.uplait.domain.plan.entity.QInternetPlan;
import com.ureca.uplait.domain.plan.entity.QMobilePlan;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class PlanRepositoryImpl implements PlanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MobilePlanAdminResponse> findAllMobilePlans(Pageable pageable) {
        QMobilePlan plan = QMobilePlan.mobilePlan;

        List<MobilePlanAdminResponse> content = queryFactory
            .selectFrom(plan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(plan.id.desc())
            .fetch()
            .stream()
            .map(MobilePlanAdminResponse::new)
            .toList();

        Long total = queryFactory
            .select(plan.count())
            .from(plan)
            .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }

    @Override
    public Page<InternetPlanAdminResponse> findAllInternetPlans(Pageable pageable) {
        QInternetPlan plan = QInternetPlan.internetPlan;

        List<InternetPlanAdminResponse> content = queryFactory
            .selectFrom(plan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(plan.id.desc())
            .fetch()
            .stream()
            .map(InternetPlanAdminResponse::new)
            .toList();

        Long total = queryFactory
            .select(plan.count())
            .from(plan)
            .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }

    @Override
    public Page<IPTVPlanAdminResponse> findAllIPTVPlans(Pageable pageable) {
        QIPTVPlan plan = QIPTVPlan.iPTVPlan;

        List<IPTVPlanAdminResponse> content = queryFactory
            .selectFrom(plan)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(plan.id.desc())
            .fetch()
            .stream()
            .map(IPTVPlanAdminResponse::new)
            .toList();

        Long total = queryFactory
            .select(plan.count())
            .from(plan)
            .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }


}
