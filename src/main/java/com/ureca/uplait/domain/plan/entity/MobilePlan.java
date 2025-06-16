package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.domain.admin.dto.request.AdminMobilePlanUpdateRequest;
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
    private MediaBenefit mediaBenefit;

    @Column(name = "duration_discount_rate")
    private Integer durationDiscountRate;

    @Column(name = "premier_discount_rate", nullable = true)
    private Integer premierDiscountRate;


    public void mobileUpdateFrom(AdminMobilePlanUpdateRequest request) {

        super.updateFrom(request);

        this.data = request.getData();
        this.sharedData = request.getSharedData();
        this.voiceCall = request.getVoiceCall();
        this.message = request.getMessage();
        this.extraData = request.getExtraData();
        this.mediaBenefit = request.getMediaBenefit();
        this.durationDiscountRate = request.getDurationDiscountRate();
        this.premierDiscountRate = request.getPremierDiscountRate();
    }
}