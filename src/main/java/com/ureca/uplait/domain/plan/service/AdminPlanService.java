package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanAdminResponse;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminPlanService {

    private final PlanRepository planRepository;

    public Long createMobilePlan(MobilePlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        MobilePlan plan = request.toMobile();
        return planRepository.save(plan).getId();
    }

    public Long createInternetPlan(InternetPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        InternetPlan plan = request.toInternet();
        return planRepository.save(plan).getId();
    }

    public Long createIptvPlan(IPTVPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        IPTVPlan plan = request.toIPTV();
        return planRepository.save(plan).getId();
    }

    public Page<MobilePlanAdminResponse> getAllMobilePlans(Pageable pageable) {
        return planRepository.findAllMobilePlans(pageable);
    }

    public Page<InternetPlanAdminResponse> getAllInternetPlans(Pageable pageable) {
        return planRepository.findAllInternetPlans(pageable);
    }

    public Page<IPTVPlanAdminResponse> getAllIPTVPlans(Pageable pageable) {
        return planRepository.findAllIPTVPlans(pageable);
    }

    public void deletePlanById(Long planId) {
        Plan plan = getPlan(planId);
        planRepository.delete(plan);
    }

    private Plan getPlan(Long id) {
        return planRepository.findById(id)
            .orElseThrow(() -> new GlobalException(ResultCode.PLAN_NOT_FOUND));
    }

    private void validateDuplicatePlanName(String name) {
        if (planRepository.existsByPlanName(name)) {
            throw new GlobalException(ResultCode.DUPLICATE_PLAN_NAME);
        }
    }

}
