package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlanRepositoryCustom {

    Page<MobilePlanDetailResponse> findAllMobilePlans(Pageable pageable, User user);

    Page<InternetPlanDetailResponse> findAllInternetPlans(Pageable pageable, User user);

    Page<IPTVPlanDetailResponse> findAllIPTVPlans(Pageable pageable, User user);
}