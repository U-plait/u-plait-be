package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanListResponse;
import com.ureca.uplait.domain.plan.entity.Plan;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanRepositoryCustom {

    Page<MobilePlanDetailResponse> findAllMobilePlans(Pageable pageable);

    Page<InternetPlanDetailResponse> findAllInternetPlans(Pageable pageable);

    Page<IPTVPlanDetailResponse> findAllIPTVPlans(Pageable pageable);

    List<PlanListResponse> findAllMobileByList();

    List<PlanListResponse> findAllInternetByList();

    List<PlanListResponse> findAllIPTVByList();

    List<Plan> findPlansByTypeAndIdIn(Class<? extends Plan> type, List<Long> ids);
}