package com.ureca.uplait.domain.plan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mobile_plan")
@DiscriminatorValue("MobilePlan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobilePlan extends Plan {

    @Column(nullable = false)
    private String data;

    @Column(name = "shared_data")
    private String sharedData;

    @Column(name = "voice_call", nullable = false)
    private String voiceCall;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "extra_data", nullable = false)
    private String extraData;

    @Column(name = "media_benefit", nullable = false)
    private Boolean mediaBenefit;

    @Column(name = "duration_discount_rate")
    private Integer durationDiscountRate;

    @Column(name = "premier_discount_rate", nullable = true)
    private Integer premierDiscountRate;
}