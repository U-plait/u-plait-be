package com.ureca.uplait.domain.plan.controller;

import com.ureca.uplait.domain.plan.dto.request.PlanCompareRequest;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanListResponse;
import com.ureca.uplait.domain.plan.service.PlanService;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/plan")
@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

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

    @Operation(summary = "모바일 요금제 목록 조회", description = "모바일 요금제 목록을 모두 조회합니다.")
    @GetMapping("/mobile")
    public CommonResponse<List<PlanListResponse>> getMobilePlans() {
        return CommonResponse.success(planService.getAllMobilePlans());
    }

    @Operation(summary = "인터넷 요금제 목록 조회", description = "인터넷 요금제 목록을 모두 조회합니다.")
    @GetMapping("/internet")
    public CommonResponse<List<PlanListResponse>> getInternetPlans() {
        return CommonResponse.success(planService.getAllInternetPlans());
    }

    @Operation(summary = "IPTV 요금제 목록 조회", description = "IPTV 요금제 목록을 모두 조회합니다.")
    @GetMapping("/iptv")
    public CommonResponse<List<PlanListResponse>> getIptvPlans() {
        return CommonResponse.success(planService.getAllIPTVPlans());
    }

    @Operation(summary = "특정 타입 내 요금제 비교", description = "지정된 타입에서 선택된 요금제들의 상세 정보를 비교합니다.")
    @PostMapping("/compare/{planType}")
    public CommonResponse<List<PlanDetailResponse>> comparePlans(
        @Parameter(description = "요금제 타입", example = "MobilePlan")
        @PathVariable String planType,
        @RequestBody PlanCompareRequest requestDto
    ) {
        return CommonResponse.success(planService.comparePlansByType(planType, requestDto.getPlanIds()));
    }

}
