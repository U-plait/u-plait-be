package com.ureca.uplait.domain.plan.controller;

import com.ureca.uplait.domain.plan.dto.resoponse.PlanDetailResponse;
import com.ureca.uplait.domain.plan.service.AdminPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminPlanController {

    private final AdminPlanService adminPlanService;

    @GetMapping("/plans/{id}")
    public PlanDetailResponse getPlanDetail(@PathVariable Long id) {
        return adminPlanService.getPlanDetail(id);
    }
}
