package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.InternetPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "인터넷 요금제 상세")
public class InternetPlanDetailResponse extends PlanDetailResponse {

    @Schema(description = "인터넷 속도", example = "500M")
    private String velocity;

    @Schema(description = "인터넷 온라인 할인 가격", example = "42900")
    private int internetDiscount;

    public InternetPlanDetailResponse(InternetPlan plan, boolean inUse) {
        super(plan, inUse);
        this.velocity = plan.getVelocity();
        this.internetDiscount = plan.getInternetDiscountRate();
    }
}
