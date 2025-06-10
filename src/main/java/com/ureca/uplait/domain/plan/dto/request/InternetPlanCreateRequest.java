package com.ureca.uplait.domain.plan.dto.request;

import com.ureca.uplait.domain.plan.entity.InternetPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "인터넷 요금제 생성 요청 DTO")
public class InternetPlanCreateRequest extends PlanCreateRequest {

    @Schema(description = "인터넷 속도", example = "1Gbps")
    private String velocity;

    @Schema(description = "인터넷 할인율", example = "20")
    private Integer internetDiscountRate;

    public InternetPlan toInternet() {
        return InternetPlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
            .velocity(velocity)
            .internetDiscountRate(internetDiscountRate)
            .build();
    }
}
