package com.ureca.uplait.domain.plan.dto.resoponse;


public record InternetResponseDto(String planName,
                                  int planPrice,
                                  boolean availability,
                                  boolean combinabiliy,
                                  int internetDiscountRate) implements PlanDetailResponse {

}
