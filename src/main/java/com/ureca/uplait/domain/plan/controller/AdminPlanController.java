package com.ureca.uplait.domain.plan.controller;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.response.PlanCreationInfoResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailAdminResponse;
import com.ureca.uplait.domain.plan.service.AdminPlanService;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/plan")
@Tag(name = "요금제 관리 API", description = "Mobile, IPTV, Internet 요금제 생성/조회/삭제")
public class AdminPlanController {

    private final AdminPlanService adminPlanService;

    @Operation(summary = "모바일 요금제 생성", description = "관리자가 모바일 요금제를 생성합니다.")
    @PostMapping("/mobile")
    public CommonResponse<Long> createMobilePlan(
        @Parameter(description = "모바일 요금제 생성 요청 DTO", required = true)
        @RequestBody MobilePlanCreateRequest request) {
        return CommonResponse.success(adminPlanService.createMobilePlan(request));
    }

    @Operation(summary = "인터넷 요금제 생성", description = "관리자가 인터넷 요금제를 생성합니다.")
    @PostMapping("/internet")
    public CommonResponse<Long> createInternetPlan(
        @Parameter(description = "인터넷 요금제 생성 요청 DTO", required = true)
        @RequestBody InternetPlanCreateRequest request) {
        return CommonResponse.success(adminPlanService.createInternetPlan(request));
    }

    @Operation(summary = "IPTV 요금제 생성", description = "관리자가 IPTV 요금제를 생성합니다.")
    @PostMapping("/iptv")
    public CommonResponse<Long> createIptvPlan(
        @Parameter(description = "IPTV 요금제 생성 요청 DTO", required = true)
        @RequestBody IPTVPlanCreateRequest request) {
        return CommonResponse.success(adminPlanService.createIptvPlan(request));
    }


    @Operation(summary = "모바일 요금제 목록 조회", description = "모바일 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/mobile")
    public CommonResponse<PageImpl<PlanDetailAdminResponse>> getMobilePlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllMobilePlans(pageable));
    }

    @Operation(summary = "인터넷 요금제 목록 조회", description = "인터넷 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/internet")
    public CommonResponse<PageImpl<PlanDetailAdminResponse>> getInternetPlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllInternetPlans(pageable));
    }

    @Operation(summary = "IPTV 요금제 목록 조회", description = "IPTV 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/iptv")
    public CommonResponse<PageImpl<PlanDetailAdminResponse>> getIptvPlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllIPTVPlans(pageable));
    }

    @Operation(summary = "관리자 요금제 상세 조회", description = "요금제 ID로 관리자 전용 상세 정보를 조회합니다.")
    @GetMapping("/{type}/detail/{planId}")
    public CommonResponse<PlanDetailAdminResponse> getPlanDetailForAdmin(
        @Parameter(description = "요금제 타입 (mobile, internet, iptv)", required = true)
        @PathVariable String type,
        @Parameter(description = "요금제 ID", required = true)
        @PathVariable Long planId
    ) {
        return CommonResponse.success(adminPlanService.getPlanDetail(planId));
    }

    @Operation(summary = "요금제 삭제 (ID 기반)", description = "요금제 ID를 기준으로 요금제를 삭제합니다.")
    @DeleteMapping("/{planId}")
    public CommonResponse<Long> deletePlanById(
        @Parameter(description = "삭제할 요금제 ID", example = "1", required = true)
        @PathVariable Long planId
    ) {
        Long deletedId = adminPlanService.deletePlanById(planId);
        return CommonResponse.success(ResultCode.PLAN_DELETE_SUCCESS, deletedId);
    }

    /**
     * 요금제 생성을 위한 정보 반환
     */
    @Operation(summary = "요금제 생성을 위한 정보 반환", description = "요금제 생성을 위한 정보 반환")
    @GetMapping("/info")
    public CommonResponse<PlanCreationInfoResponse> getTags() {
        return CommonResponse.success(adminPlanService.getPlanCreationInfo());
    }

}
