package com.ureca.uplait.domain.plan.controller;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateRequest;
import com.ureca.uplait.domain.plan.service.AdminPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plans")
@Tag(name = "요금제 관리 API", description = "Mobile, IPTV, Internet 요금제 생성/조회/삭제")
public class AdminPlanController {

    private final AdminPlanService adminPlanService;


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


    @Operation(summary = "요금제 삭제")
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId) {
        adminPlanService.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }
}
