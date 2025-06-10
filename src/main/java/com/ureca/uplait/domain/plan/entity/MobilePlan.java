package com.ureca.uplait.domain.plan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "mobile_plan")
@DiscriminatorValue("MobilePlan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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

    @Column(name = "description", nullable = false)
    private String description;
}