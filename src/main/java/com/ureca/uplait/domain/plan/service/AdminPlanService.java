package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateRequest;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
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

    private Plan getPlan(Long id) {
        return planRepository.findById(id)
            .orElseThrow(() -> new GlobalException(ResultCode.PLAN_NOT_FOUND));
    }

    public void deletePlanByName(String planName) {
        Plan plan = planRepository.findByPlanName(planName)
            .orElseThrow(() -> new GlobalException(ResultCode.PLAN_NOT_FOUND));
        planRepository.delete(plan);
    }


    private void validateDuplicatePlanName(String name) {
        if (planRepository.existsByPlanName(name)) {
            throw new GlobalException(ResultCode.DUPLICATE_PLAN_NAME);
        }
    }

}
