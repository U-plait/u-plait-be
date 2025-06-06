package com.ureca.uplait.domain.community.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "community_plan_price")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityPlanPrice extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "community_plan_id", nullable = false)
    private CommunityPlan communityPlan;

    @Column(nullable = false)
    private Integer headcount;

    @Column(name = "youth_discount", nullable = true)
    private Integer youthDiscount;

    @Column(name = "mobile_discount", nullable = true)
    private Integer mobileDiscount;

    @Column(name = "internet_discount", nullable = true)
    private Integer internetDiscount;
}
