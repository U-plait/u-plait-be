package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "plan")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
@NoArgsConstructor
@SuperBuilder
public abstract class Plan extends BaseEntity {

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "plan_price", nullable = false)
    private Integer planPrice;

    @Column(name = "plan_benefit", nullable = false)
    private String planBenefit;

    @Column(name = "availability", nullable = false)
    private Boolean availability;

    @Column(name = "description", nullable = true)
    private String description;
}