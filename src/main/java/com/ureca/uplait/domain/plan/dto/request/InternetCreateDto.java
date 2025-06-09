package com.ureca.uplait.domain.plan.dto.request;

public record InternetCreateDto(String planName,
                                Integer planPrice,
                                String planBenefit,
                                Boolean avaliability,
                                Boolean combinabiliy,
                                String velocity,
                                Integer internetDiscountRate) {

}
