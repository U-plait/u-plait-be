package com.ureca.uplait.domain.plan.repository;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailAdminResponse;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.entity.QIPTVPlan;
import com.ureca.uplait.domain.plan.entity.QInternetPlan;
import com.ureca.uplait.domain.plan.entity.QMobilePlan;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class PlanRepositoryImpl implements PlanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public PageImpl<PlanDetailAdminResponse> findAllMobilePlans(Pageable pageable) {
        return fetchPlans(pageable, QMobilePlan.mobilePlan);
    }

    @Override
    public PageImpl<PlanDetailAdminResponse> findAllInternetPlans(Pageable pageable) {
        return fetchPlans(pageable, QInternetPlan.internetPlan);
    }

    @Override
    public PageImpl<PlanDetailAdminResponse> findAllIPTVPlans(Pageable pageable) {
        return fetchPlans(pageable, QIPTVPlan.iPTVPlan);
    }

    private <T extends Plan> PageImpl<PlanDetailAdminResponse> fetchPlans(Pageable pageable,
        EntityPathBase<T> planPath) {

        PathBuilder<T> pathBuilder = new PathBuilder<>(planPath.getType(), planPath.getMetadata());

        List<PlanDetailAdminResponse> content = queryFactory
            .selectFrom(planPath)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(pathBuilder.getNumber("id", Long.class).desc())
            .fetch()
            .stream()
            .map(entity -> new PlanDetailAdminResponse((Plan) entity))
            .toList();

        Long total = queryFactory
            .select(planPath.count())
            .from(planPath)
            .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }
}
