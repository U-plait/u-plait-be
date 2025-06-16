package com.ureca.uplait.domain.admin.dto.request;

import com.ureca.uplait.domain.plan.entity.InternetPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "인터넷 요금제 수정 요청 DTO")
public class AdminInternetPlanCreateRequest extends PlanCommonRequest {
    @Schema(description = "인터넷 속도", example = "1Gbps")
    private String velocity;

    @Schema(description = "인터넷 할인율 (%)", example = "10")
    private Integer internetDiscountRate;

    public InternetPlan toInternet() {
        return InternetPlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
            .description(getDescription())
            .velocity(velocity)
            .internetDiscountRate(internetDiscountRate)
            .build();
    }
}
