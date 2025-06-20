package com.ureca.uplait.domain.plan.controller;

import com.ureca.uplait.domain.admin.service.AdminPlanService;
import com.ureca.uplait.domain.plan.dto.request.PlanCompareRequest;
import com.ureca.uplait.domain.plan.dto.response.*;
import com.ureca.uplait.domain.plan.service.PlanService;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/plan")
@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final AdminPlanService adminPlanService;

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

    @Operation(summary = "모바일 비교 요금제 목록 조회", description = "모바일 요금제 목록을 모두 조회합니다.")
    @GetMapping("/Cmobile")
    public CommonResponse<List<PlanListResponse>> getMobilePlans() {
        return CommonResponse.success(planService.getAllMobilePlans());
    }

    @Operation(summary = "인터넷 비교 요금제 목록 조회", description = "인터넷 요금제 목록을 모두 조회합니다.")
    @GetMapping("/Cinternet")
    public CommonResponse<List<PlanListResponse>> getInternetPlans() {
        return CommonResponse.success(planService.getAllInternetPlans());
    }

    @Operation(summary = "IPTV 비교 요금제 목록 조회", description = "IPTV 요금제 목록을 모두 조회합니다.")
    @GetMapping("/Ciptv")
    public CommonResponse<List<PlanListResponse>> getIptvPlans() {
        return CommonResponse.success(planService.getAllIPTVPlans());
    }

    @Operation(summary = "모바일 요금제 목록 조회", description = "모바일 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/mobile")
    public CommonResponse<Page<MobilePlanDetailResponse>> getMobilePlans(
        @Parameter(description = "사용자정보", required = false)
        @AuthenticationPrincipal User user,
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllMobilePlans(pageable, user));
    }

    @Operation(summary = "인터넷 요금제 목록 조회", description = "인터넷 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/internet")
    public CommonResponse<Page<InternetPlanDetailResponse>> getInternetPlans(
        @Parameter(description = "사용자정보", required = false)
        @AuthenticationPrincipal User user,
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllInternetPlans(pageable, user));
    }

    @Operation(summary = "IPTV 요금제 목록 조회", description = "IPTV 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/iptv")
    public CommonResponse<Page<IPTVPlanDetailResponse>> getIptvPlans(
        @Parameter(description = "사용자정보", required = false)
        @AuthenticationPrincipal User user,
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllIPTVPlans(pageable, user));
    }

    @Operation(summary = "특정 타입 내 요금제 비교", description = "지정된 타입에서 선택된 요금제들의 상세 정보를 비교합니다.")
    @PostMapping("/compare/{planType}")
    public CommonResponse<List<PlanCompareResponse>> comparePlans(
        @Parameter(description = "요금제 타입", example = "MobilePlan")
        @PathVariable String planType,
        @RequestBody PlanCompareRequest requestDto
    ) {
        return CommonResponse.success(
            planService.comparePlansByType(planType, requestDto.getPlanIds()));
    }
}
