package com.ureca.uplait.domain.plan.dto.request;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IPTV 요금제 생성 요청 DTO")
public class IPTVPlanCreateRequest extends PlanCreateRequest {

    @Schema(description = "제공 채널 수", example = "220")
    private Integer channel;

    @Schema(description = "IPTV 할인율", example = "15")
    private Integer iptvDiscountRate;

    public IPTVPlan toIPTV() {
        return IPTVPlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
            .description(getDescription())
            .channel(channel)
            .iptvDiscountRate(iptvDiscountRate)
            .build();
    }
}
