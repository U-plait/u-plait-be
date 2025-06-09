package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanResponseFactory;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ureca.uplait.global.response.ResultCode.NOT_FOUND_PLAN;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    /**
     * 요금제 상세 조회
     */
    public PlanDetailResponse getPlanDetail(Long planId) {
        Plan plan = findPlan(planId);
        return PlanResponseFactory.from(plan);
    }

    private Plan findPlan(Long planId) {
        return planRepository.findById(planId).orElseThrow(() -> new GlobalException(NOT_FOUND_PLAN));
    }
}
