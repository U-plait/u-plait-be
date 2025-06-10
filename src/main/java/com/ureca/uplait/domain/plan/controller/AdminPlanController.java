package com.ureca.uplait.domain.plan.controller;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.domain.plan.service.AdminPlanService;
import com.ureca.uplait.domain.plan.service.PlanService;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/plan")
@Tag(name = "요금제 관리 API", description = "Mobile, IPTV, Internet 요금제 생성/조회/삭제")
public class AdminPlanController {

    private final AdminPlanService adminPlanService;
    private final PlanService planService;

    @Operation(summary = "모바일 요금제 생성")
    @PostMapping("/mobile")
    public ResponseEntity<Long> createMobilePlan(
        @RequestBody @Valid MobilePlanCreateRequest request) {
        return ResponseEntity.ok(adminPlanService.createMobilePlan(request));
    }

    @Operation(summary = "인터넷 요금제 생성")
    @PostMapping("/internet")
    public ResponseEntity<Long> createInternetPlan(
        @RequestBody @Valid InternetPlanCreateRequest request) {
        return ResponseEntity.ok(adminPlanService.createInternetPlan(request));
    }

    @Operation(summary = "IPTV 요금제 생성")
    @PostMapping("/iptv")
    public ResponseEntity<Long> createIptvPlan(@RequestBody @Valid IPTVPlanCreateRequest request) {
        return ResponseEntity.ok(adminPlanService.createIptvPlan(request));
    }

    @Operation(summary = "요금제 상세 조회", description = "요금제 상세 조회")
    @GetMapping("/{planId}")
    public CommonResponse<PlanDetailResponse> getPlanDetail(
        @Parameter(description = "사용자정보")
        @AuthenticationPrincipal User user,
        @Parameter(description = "요금제 id")
        @PathVariable Long planId
    ) {
        return CommonResponse.success(planService.getPlanDetail(user, planId));
    }

    @DeleteMapping("/name/{planName}")
    @Operation(summary = "요금제 삭제 (이름 기반)", description = "요금제 이름으로 요금제를 삭제합니다.")
    public ResponseEntity<Void> deletePlanByName(@PathVariable String planName) {
        adminPlanService.deletePlanByName(planName);
        return ResponseEntity.noContent().build();
    }
}
