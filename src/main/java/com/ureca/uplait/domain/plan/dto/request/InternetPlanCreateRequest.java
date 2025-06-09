package com.ureca.uplait.domain.plan.dto.request;

import com.ureca.uplait.domain.plan.entity.InternetPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InternetPlanCreateRequest extends PlanCreateRequest {

    private String velocity;

    private Integer internetDiscountRate;

    public InternetPlan toInternet() {
        return InternetPlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
            .combinability(getCombinability())
            .velocity(velocity)
            .internetDiscountRate(internetDiscountRate)
            .build();
    }
}
