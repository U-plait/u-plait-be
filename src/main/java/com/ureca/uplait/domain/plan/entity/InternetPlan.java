package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "internet_plan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternetPlan extends BaseEntity {

    @Column(nullable = false)
    private String velocity;

    @Column(name = "internet_discount_rate", nullable = true)
    private Integer internetDiscountRate;

    @OneToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}
