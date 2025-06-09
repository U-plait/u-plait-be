package com.ureca.uplait.domain.plan.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class PlanCreateRequest {

    private String planName;

    private Integer planPrice;

    private String planBenefit;

    private Boolean availability;

    private Boolean combinability;
}
