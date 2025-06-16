package com.ureca.uplait.domain.admin.controller;

import com.ureca.uplait.domain.admin.dto.request.AdminIPTVPlanCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminIPTVPlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminInternetPlanCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminInternetPlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminMobileCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminMobilePlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanCreateResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanDeleteResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanDetailResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminUpdateAllVectorResponse;
import com.ureca.uplait.domain.admin.service.AdminPlanService;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanCreationInfoResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/plan")
@Tag(name = "요금제 관리 API", description = "Mobile, IPTV, Internet 요금제 생성/조회/삭제")
public class AdminPlanController {

    private final AdminPlanService adminPlanService;

    @Operation(summary = "모바일 요금제 생성", description = "관리자가 새로운 모바일 요금제를 등록합니다.")
    @PostMapping("/mobile")
    public CommonResponse<AdminPlanCreateResponse> createMobilePlan(
        @RequestBody AdminMobileCreateRequest request) {
        return CommonResponse.success(ResultCode.PLAN_CREATE_SUCCESS,
            adminPlanService.createMobilePlan(request));
    }

    @Operation(summary = "인터넷 요금제 생성", description = "관리자가 새로운 인터넷 요금제를 등록합니다.")
    @PostMapping("/internet")
    public CommonResponse<AdminPlanCreateResponse> createInternetPlan(
        @RequestBody AdminInternetPlanCreateRequest request) {
        return CommonResponse.success(ResultCode.PLAN_CREATE_SUCCESS,
            adminPlanService.createInternetPlan(request));
    }

    @Operation(summary = "IPTV 요금제 생성", description = "관리자가 새로운 IPTV 요금제를 등록합니다.")
    @PostMapping("/iptv")
    public CommonResponse<AdminPlanCreateResponse> createIptvPlan(
        @RequestBody AdminIPTVPlanCreateRequest request) {
        return CommonResponse.success(ResultCode.PLAN_CREATE_SUCCESS,
            adminPlanService.createIptvPlan(request));
    }

    /**
     * 요금제 목록 조회
     */
    @Operation(summary = "모바일 요금제 목록 조회", description = "모바일 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/mobile")
    public CommonResponse<Page<MobilePlanDetailResponse>> getMobilePlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllMobilePlans(pageable));
    }

    @Operation(summary = "인터넷 요금제 목록 조회", description = "인터넷 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/internet")
    public CommonResponse<Page<InternetPlanDetailResponse>> getInternetPlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllInternetPlans(pageable));
    }

    @Operation(summary = "IPTV 요금제 목록 조회", description = "IPTV 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/iptv")
    public CommonResponse<Page<IPTVPlanDetailResponse>> getIptvPlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllIPTVPlans(pageable));
    }

    @Operation(summary = "모바일 요금제 수정", description = "모바일 요금제의 상세 정보를 수정합니다.")
    @PutMapping("/mobile/{planId}")
    public CommonResponse<AdminPlanDetailResponse> updateMobilePlan(
        @PathVariable Long planId,
        @RequestBody AdminMobilePlanUpdateRequest request
    ) {
        adminPlanService.updateMobilePlan(planId, request);
        return CommonResponse.success(ResultCode.PLAN_UPDATE_SUCCESS, adminPlanService.getPlanDetail(planId));
    }

    @Operation(summary = "인터넷 요금제 수정", description = "인터넷 요금제의 상세 정보를 수정합니다.")
    @PutMapping("/internet/{planId}")
    public CommonResponse<AdminPlanDetailResponse> updateInternetPlan(
        @PathVariable Long planId,
        @RequestBody AdminInternetPlanUpdateRequest request
    ) {
        adminPlanService.updateInternetPlan(planId, request);
        return CommonResponse.success(ResultCode.PLAN_UPDATE_SUCCESS, adminPlanService.getPlanDetail(planId));
    }

    @Operation(summary = "IPTV 요금제 수정", description = "IPTV 요금제의 상세 정보를 수정합니다.")
    @PutMapping("/iptv/{planId}")
    public CommonResponse<AdminPlanDetailResponse> updateIPTVPlan(
        @PathVariable Long planId,
        @RequestBody AdminIPTVPlanUpdateRequest request
    ) {
        adminPlanService.updateIPTVPlan(planId, request);
        return CommonResponse.success(ResultCode.PLAN_UPDATE_SUCCESS, adminPlanService.getPlanDetail(planId));
    }

    @Operation(summary = "관리자 요금제 상세 조회", description = "요금제 ID로 관리자 전용 상세 정보를 조회합니다.")
    @GetMapping("/{type}/detail/{planId}")
    public CommonResponse<? extends PlanDetailResponse> getPlanDetailForAdmin(
        @Parameter(description = "요금제 타입 (MobilePlan, InternetPlan, IPTVPlan)", required = true)
        @PathVariable String type,
        @Parameter(description = "요금제 ID", required = true)
        @PathVariable Long planId
    ) {
        return CommonResponse.success(adminPlanService.getTypedPlanDetail(type, planId));
    }

    @Operation(summary = "요금제 삭제 (ID 기반)", description = "요금제 ID를 기준으로 요금제를 삭제합니다.")
    @DeleteMapping("/{planId}")
    public CommonResponse<AdminPlanDeleteResponse> deletePlanById(
        @Parameter(description = "삭제할 요금제 ID", example = "1", required = true)
        @PathVariable Long planId
    ) {
        return CommonResponse.success(ResultCode.PLAN_DELETE_SUCCESS, adminPlanService.deletePlanById(planId));
    }

    @Operation(summary = "요금제 생성/수정 페이지 정보 반환", description = "태그 List와 결합 혜택 List를 반환합니다.")
    @GetMapping("/Info")
    public CommonResponse<PlanCreationInfoResponse> getPlanCreationInfo() {
        return CommonResponse.success(adminPlanService.getPlanCreationInfo());
    }

    @Operation(summary = "(임시) 전체 vector 정보 갱신", description = "(임시) 전체 vector 정보 갱신")
    @PostMapping("/vector")
    public CommonResponse<AdminUpdateAllVectorResponse> updateAllVector() {
        return CommonResponse.success(adminPlanService.updateAllVector());
    }
}
