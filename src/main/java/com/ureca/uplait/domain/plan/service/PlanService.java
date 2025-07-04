package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.community.repository.PlanCommunityRepository;
import com.ureca.uplait.domain.contract.repository.ContractRepository;
import com.ureca.uplait.domain.plan.dto.response.*;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.IPTVPlanRepository;
import com.ureca.uplait.domain.plan.repository.InternetPlanRepository;
import com.ureca.uplait.domain.plan.repository.MobilePlanRepository;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.ureca.uplait.global.response.ResultCode.INVALID_INPUT;
import static com.ureca.uplait.global.response.ResultCode.PLAN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final ContractRepository contractRepository;
    private final MobilePlanRepository mobilePlanRepository;
    private final InternetPlanRepository internetPlanRepository;
    private final IPTVPlanRepository iptvPlanRepository;
    private final PlanCommunityRepository planCommunityRepository;

    @Transactional(readOnly = true)
    public PlanDetailResponse getPlanDetail(User user, Long planId) {
        Plan plan = findPlan(planId);
        List<Long> communityIdList = planCommunityRepository.findAllByPlan(plan).stream()
            .map(pc -> pc.getCommunityBenefit().getId()).toList();
        boolean inUse = contractRepository.existsByUserIdAndPlanId(user.getId(), planId);
        return PlanResponseFactory.from(plan, communityIdList, inUse);
    }

    private Plan findPlan(Long planId) {
        return planRepository.findById(planId)
            .orElseThrow(() -> new GlobalException(PLAN_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<PlanListResponse> getAllMobilePlans() {
        return mobilePlanRepository.findAllByOrderByIdDesc().stream()
            .map(PlanListResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PlanListResponse> getAllInternetPlans() {
        return internetPlanRepository.findAllByOrderByIdDesc().stream()
            .map(PlanListResponse::new)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PlanListResponse> getAllIPTVPlans() {
        return iptvPlanRepository.findAllByOrderByIdDesc().stream()
            .map(PlanListResponse::new)
            .collect(Collectors.toList());
    }

    public List<PlanCompareResponse> comparePlansByType(String planType, List<Long> planIds) {
        if (planIds == null || planIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<? extends Plan> plans;
        switch (planType) {
            case "MobilePlan" -> plans = mobilePlanRepository.findAllById(planIds);
            case "InternetPlan" -> plans = internetPlanRepository.findAllById(planIds);
            case "IPTVPlan" -> plans = iptvPlanRepository.findAllById(planIds);
            default -> throw new GlobalException(INVALID_INPUT);
        }

        return plans.stream()
            .map(PlanCompareFactory::from)
            .collect(Collectors.toList());
    }
}
