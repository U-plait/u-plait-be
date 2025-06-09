package com.ureca.uplait.domain.plan.dto.request;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MobilePlanCreateRequest extends PlanCreateRequest {

    private String data;

    private String sharedData;

    private String voiceCall;

    private String message;

    private String extraData;

    private Boolean mediaBenefit;

    private Integer durationDiscountRate;

    private Integer premierDiscountRate;

    public MobilePlan toMobile() {
        return MobilePlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
            .combinability(getCombinability())
            .data(data)
            .sharedData(sharedData)
            .voiceCall(voiceCall)
            .message(message)
            .extraData(extraData)
            .mediaBenefit(mediaBenefit)
            .durationDiscountRate(durationDiscountRate)
            .premierDiscountRate(premierDiscountRate)
            .build();
    }
}
