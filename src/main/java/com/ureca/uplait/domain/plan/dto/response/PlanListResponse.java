package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.Plan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // Lombok의 NoArgsConstructor 추가
@Schema(description = "요금제 목록 조회 결과 (이름, 가격만 포함)")
public class PlanListResponse {

    @Schema(description = "요금제 id", example = "1")
    private Long planId;

    @Schema(description = "요금제 이름", example = "5G 프리미어 에센셜")
    private String planName;

    @Schema(description = "요금제 가격", example = "59000")
    private Integer planPrice;

    public PlanListResponse(Plan plan) {
        this.planId = plan.getId();
        this.planName = plan.getPlanName();
        this.planPrice = plan.getPlanPrice();
    }
}