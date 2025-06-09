package com.ureca.uplait.domain.plan.dto.resoponse;

public record IptvResponseDto(String planName,
                              int planPrice,
                              boolean availability,
                              boolean combinabiliy,
                              int iptvDiscountRate) implements PlanDetailResponse {

}
