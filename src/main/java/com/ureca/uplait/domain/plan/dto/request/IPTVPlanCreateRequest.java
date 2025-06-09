package com.ureca.uplait.domain.plan.dto.request;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IPTVPlanCreateRequest extends PlanCreateRequest {

    private Integer channel;

    private Integer iptvDiscountRate;

    public IPTVPlan toIPTV() {
        return IPTVPlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
            .combinability(getCombinability())
            .channel(channel)
            .iptvDiscountRate(iptvDiscountRate)
            .build();
    }
}
