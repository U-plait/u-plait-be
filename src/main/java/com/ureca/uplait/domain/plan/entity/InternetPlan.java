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
@Table(name = "internet_plan")
@DiscriminatorValue("InternetPlan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InternetPlan extends Plan {

    @Column(nullable = false)
    private String velocity;

    @Column(name = "internet_discount_rate", nullable = true)
    private Integer internetDiscountRate;

    public InternetPlan(String planName, Integer planPrice, String planBenefit,
        Boolean availability,
        Boolean combinability, String velocity, Integer internetDiscountRate) {
        super(planName, planPrice, planBenefit, availability, combinability);
        this.velocity = velocity;
        this.internetDiscountRate = internetDiscountRate;
    }
}
