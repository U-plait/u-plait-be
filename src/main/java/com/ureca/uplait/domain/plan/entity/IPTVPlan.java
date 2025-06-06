package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "iptv_plan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IPTVPlan extends BaseEntity {

    @Column(nullable = true)
    private Integer channel;

    @Column(name = "iptv_discount_rate", nullable = true)
    private Integer iptvDiscountRate;

    @OneToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}