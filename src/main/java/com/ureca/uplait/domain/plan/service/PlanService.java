package com.ureca.uplait.domain.plan.service;

import static com.ureca.uplait.global.response.ResultCode.INVALID_INPUT;
import static com.ureca.uplait.global.response.ResultCode.PLAN_NOT_FOUND;

import com.ureca.uplait.domain.contract.repository.ContractRepository;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanListResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanResponseFactory;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.plan.repository.PlanRepositoryCustomImpl;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.exception.GlobalException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final ContractRepository contractRepository;
    private final PlanRepositoryCustomImpl planRepositoryCustom;

    /**
     * 요금제 상세 조회
     */
    @Transactional(readOnly = true)
    public PlanDetailResponse getPlanDetail(User user, Long planId) {
        Plan plan = findPlan(planId);
        boolean inUse = contractRepository.existsByUserIdAndPlanId(user.getId(), planId);
        return PlanResponseFactory.from(plan, inUse);
    }

    private Plan findPlan(Long planId) {
        return planRepository.findById(planId)
            .orElseThrow(() -> new GlobalException(PLAN_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<PlanListResponse> getAllMobilePlans() {
        return planRepositoryCustom.findAllMobileByList();
    }

    @Transactional(readOnly = true)
    public List<PlanListResponse> getAllInternetPlans() {
        return planRepositoryCustom.findAllInternetByList();
    }

    @Transactional(readOnly = true)
    public List<PlanListResponse> getAllIPTVPlans() {
        return planRepositoryCustom.findAllIPTVByList();
    }

    public List<PlanDetailResponse> comparePlansByType(String planType, List<Long> planIds) {
        if (planIds == null || planIds.isEmpty()) {
            return Collections.emptyList();
        }

        Class<? extends Plan> targetClass;
        if ("MobilePlan".equals(planType)) {
            targetClass = MobilePlan.class;
        } else if ("InternetPlan".equals(planType)) {
            targetClass = InternetPlan.class;
        } else if ("IPTVPlan".equals(planType)) {
            targetClass = IPTVPlan.class;
        } else {
            throw new GlobalException(INVALID_INPUT);
        }

        List<Plan> plans = planRepositoryCustom.findPlansByTypeAndIdIn(targetClass, planIds);

        return plans.stream()
            .map(PlanResponseFactory::from)
            .collect(Collectors.toList());
    }
}
