package com.ureca.uplait.domain.plan.controller;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanAdminResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @Operation(summary = "모바일 요금제 생성", description = "관리자가 모바일 요금제를 생성합니다.")
    @PostMapping("/mobile")
    public ResponseEntity<Long> createMobilePlan(
        @Parameter(description = "모바일 요금제 생성 요청 DTO", required = true)
        @RequestBody @Valid MobilePlanCreateRequest request) {
        return ResponseEntity.ok(adminPlanService.createMobilePlan(request));
    }

    @Operation(summary = "인터넷 요금제 생성", description = "관리자가 인터넷 요금제를 생성합니다.")
    @PostMapping("/internet")
    public ResponseEntity<Long> createInternetPlan(
        @Parameter(description = "인터넷 요금제 생성 요청 DTO", required = true)
        @RequestBody @Valid InternetPlanCreateRequest request) {
        return ResponseEntity.ok(adminPlanService.createInternetPlan(request));
    }

    @Operation(summary = "IPTV 요금제 생성", description = "관리자가 IPTV 요금제를 생성합니다.")
    @PostMapping("/iptv")
    public ResponseEntity<Long> createIptvPlan(
        @Parameter(description = "IPTV 요금제 생성 요청 DTO", required = true)
        @RequestBody @Valid IPTVPlanCreateRequest request) {
        return ResponseEntity.ok(adminPlanService.createIptvPlan(request));
    }

    @Operation(summary = "요금제 상세 조회", description = "사용자 인증 후 특정 요금제의 상세 정보를 조회합니다.")
    @GetMapping("/{planId}")
    public CommonResponse<PlanDetailResponse> getPlanDetail(
        @Parameter(description = "인증된 사용자 정보", required = true)
        @AuthenticationPrincipal User user,
        @Parameter(description = "요금제 ID", example = "1", required = true)
        @PathVariable Long planId
    ) {
        return CommonResponse.success(planService.getPlanDetail(user, planId));
    }

    @Operation(summary = "인터넷 요금제 목록 조회", description = "인터넷 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/internet")
    public CommonResponse<Page<InternetPlanAdminResponse>> getInternetPlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllInternetPlans(pageable));
    }

    @Operation(summary = "IPTV 요금제 목록 조회", description = "IPTV 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/iptv")
    public CommonResponse<Page<IPTVPlanAdminResponse>> getIptvPlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllIPTVPlans(pageable));
    }

    @Operation(summary = "모바일 요금제 목록 조회", description = "모바일 요금제 목록을 5개씩 페이지네이션하여 조회합니다.")
    @GetMapping("/mobile")
    public CommonResponse<Page<MobilePlanAdminResponse>> getMobilePlans(
        @Parameter(description = "페이지 정보 (기본값: size=5)")
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return CommonResponse.success(adminPlanService.getAllMobilePlans(pageable));
    }

    @Operation(summary = "요금제 삭제 (ID 기반)", description = "요금제 ID를 기준으로 요금제를 삭제합니다.")
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlanById(
        @Parameter(description = "삭제할 요금제 ID", example = "1", required = true)
        @PathVariable Long planId) {
        adminPlanService.deletePlanById(planId);
        return ResponseEntity.noContent().build();
    }
}
