package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.dto.response.IPTVPlanAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanRepositoryCustom {

    Page<MobilePlanAdminResponse> findAllMobilePlans(Pageable pageable);

    Page<InternetPlanAdminResponse> findAllInternetPlans(Pageable pageable);

    Page<IPTVPlanAdminResponse> findAllIPTVPlans(Pageable pageable);
}