package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanUpdateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanUpdateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanUpdateRequest;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanResponseFactory;
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

    public Long createMobilePlan(MobilePlan request) {
        validateDuplicatePlanName(request.getPlanName());
        MobilePlan plan = request.toMobile();
        return planRepository.save(plan).getId();
    }

    public Long createInternetPlan(InternetPlan request) {
        validateDuplicatePlanName(request.getPlanName());
        InternetPlan plan = request.toInternet();
        return planRepository.save(plan).getId();
    }

    public Long createIptvPlan(IPTVPlan request) {
        validateDuplicatePlanName(request.getPlanName());
        IPTVPlan plan = request.toIPTV();
        return planRepository.save(plan).getId();
    }

    public void updateMobilePlan(Long id, MobilePlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof MobilePlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }
        ((MobilePlan) plan).mobileUpdateFrom(request);
    }

    public void updateIPTVPlan(Long id, IPTVPlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof IPTVPlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }
        ((IPTVPlan) plan).IPTVUpdateForm(request);

    }

    public void updateInternetPlan(Long id, InternetPlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof InternetPlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }
        ((InternetPlan) plan).InternetUpdateForm(request);
    }


    @Transactional(readOnly = true)
    public Page<MobilePlanDetailResponse> getAllMobilePlans(Pageable pageable) {
        return planRepository.findAllMobilePlans(pageable);
    }

    @Transactional(readOnly = true)
    public Page<InternetPlanDetailResponse> getAllInternetPlans(Pageable pageable) {
        return planRepository.findAllInternetPlans(pageable);
    }

    @Transactional(readOnly = true)
    public Page<IPTVPlanDetailResponse> getAllIPTVPlans(Pageable pageable) {
        return planRepository.findAllIPTVPlans(pageable);
    }

    @Transactional(readOnly = true)
    public PlanDetailAdminResponse getPlanDetail(Long planId) {
        Plan plan = getPlan(planId);
        return new PlanDetailAdminResponse(plan);
    }

    public PlanDetailResponse getTypedPlanDetail(String type, Long planId) {
        Plan plan = getPlan(planId);
        return PlanResponseFactory.from(plan);
    }

    public Long deletePlanById(Long planId) {
        Plan plan = getPlan(planId);
        planRepository.delete(plan);
        return planId;
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
