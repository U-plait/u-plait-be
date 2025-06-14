package com.ureca.uplait.domain.plan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ureca.uplait.domain.plan.entity.Plan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "요금제 상세 조회 결과")
public class PlanDetailAdminResponse {

    @Schema(description = "요금제 id", example = "1")
    public Long planId;

    @Schema(description = "요금제 이름", example = "5G 프리미어 에센셜")
    public String planName;

    @Schema(description = "요금제 가격", example = "59000")
    public Integer planPrice;

    @Schema(description = "요금제 혜택", example = "U+ 모바일 TV 기본 월정액 무료")
    public String planBenefit;

    @Schema(description = "등록 가능 여부", example = "true")
    public Boolean availability;

    @Schema(description = "요금제 사용 여부", example = "true")
    public Boolean inUse;

    public PlanDetailAdminResponse(Plan plan) {
        this.planId = plan.getId();
        this.planName = plan.getPlanName();
        this.planPrice = plan.getPlanPrice();
        this.planBenefit = plan.getPlanBenefit();
        this.availability = plan.getAvailability();
    }
}
