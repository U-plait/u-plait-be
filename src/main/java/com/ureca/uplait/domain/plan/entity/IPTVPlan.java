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
@Table(name = "iptv_plan")
@DiscriminatorValue("IPTVPlan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class IPTVPlan extends Plan {

    @Column(nullable = true)
    private Integer channel;

    @Column(name = "iptv_discount_rate", nullable = true)
    private Integer iptvDiscountRate;

    public IPTVPlan(String planName, Integer planPrice, String planBenefit, Boolean availability,
        Boolean combinability, Integer channel, Integer iptvDiscountRate) {
        super(planName, planPrice, planBenefit, availability, combinability);
        this.channel = channel;
        this.iptvDiscountRate = iptvDiscountRate;
    }
}