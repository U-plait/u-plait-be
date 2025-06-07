package com.ureca.uplait.domain.plan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "iptv_plan")
@DiscriminatorValue("IPTVPlan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IPTVPlan extends Plan {

    @Column(nullable = true)
    private Integer channel;

    @Column(name = "iptv_discount_rate", nullable = true)
    private Integer iptvDiscountRate;
}