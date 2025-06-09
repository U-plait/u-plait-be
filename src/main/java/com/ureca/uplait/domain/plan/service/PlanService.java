package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.contract.repository.ContractRepository;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanResponseFactory;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ureca.uplait.global.response.ResultCode.NOT_FOUND_PLAN;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final ContractRepository contractRepository;

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
        return planRepository.findById(planId).orElseThrow(() -> new GlobalException(NOT_FOUND_PLAN));
    }
}
