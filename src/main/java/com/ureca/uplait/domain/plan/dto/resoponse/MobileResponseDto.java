package com.ureca.uplait.domain.plan.dto.resoponse;

public record MobileResponseDto(String planName,
                                int planPrice,
                                boolean availability,
                                boolean combinabiliy,
                                int mobileDiscountRate,
                                String sharedData,
                                String voiceCall,
                                String message,
                                String extraDate,
                                boolean mediaBenefit) implements PlanDetailResponse {

}
