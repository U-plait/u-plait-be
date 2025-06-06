package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mobile_plan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobilePlan extends BaseEntity {

    @Column(nullable = false)
    private String data;

    @Column(name = "shared_data")
    private String sharedData;

    @Column(name = "voice_call", nullable = false)
    private String voiceCall;

    @Column(name = "duration_discount_rate")
    private Integer durationDiscountRate;

    @Column(name = "premier_discount_rate", nullable = true)
    private Integer premierDiscountRate;

    @OneToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}