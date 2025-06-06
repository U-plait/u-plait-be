package com.ureca.uplait.domain.plan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "internet_plan")
@DiscriminatorValue("InternetPlan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternetPlan extends Plan {

    @Column(nullable = false)
    private String velocity;

    @Column(name = "internet_discount_rate", nullable = true)
    private Integer internetDiscountRate;

    @OneToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}
