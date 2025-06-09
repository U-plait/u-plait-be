package com.ureca.uplait.domain.plan.controller;

import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.domain.plan.service.PlanService;
import com.ureca.uplait.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/plan")
@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    /**
     * 요금제 상세 조회
     *
     * @param planId
     */
    @Operation(summary = "요금제 상세 조회", description = "요금제 상세 조회")
    @GetMapping("/{planId}")
    public CommonResponse<PlanDetailResponse> getPlanDetail(
        @Parameter(description = "요금제 id")
        @PathVariable Long planId
    ) {
        return CommonResponse.success(planService.getPlanDetail(planId));
    }

}
