package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="plan")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public abstract class Plan extends BaseEntity {
    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "plan_price", nullable = false)
    private Integer planPrice;

    @Column(name = "plan_benefit", nullable = false)
    private String planBenefit;

    @Column(name = "availability", nullable = false)
    private Boolean availability;
}