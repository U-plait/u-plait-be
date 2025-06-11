package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.dto.response.PlanDetailAdminResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface PlanRepositoryCustom {

    PageImpl<PlanDetailAdminResponse> findAllMobilePlans(Pageable pageable);

    PageImpl<PlanDetailAdminResponse> findAllInternetPlans(Pageable pageable);

    PageImpl<PlanDetailAdminResponse> findAllIPTVPlans(Pageable pageable);
}