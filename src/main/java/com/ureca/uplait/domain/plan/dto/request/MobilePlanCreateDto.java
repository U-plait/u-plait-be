package com.ureca.uplait.domain.plan.dto.request;

public record MobilePlanCreateDto(

    String planName,
    Integer planPrice,
    String planBenefit,
    Boolean availability,
    Boolean combinabiliy,

    String data,
    String sharedData,
    String voiceCall,
    Integer durationDiscountRate,
    Integer premierDiscountRate,

    Long communityId,
    Integer communityMobileDiscountRate

) {


}
