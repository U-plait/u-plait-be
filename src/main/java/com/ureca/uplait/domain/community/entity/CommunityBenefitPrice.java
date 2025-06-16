package com.ureca.uplait.domain.community.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "community_benefit_price")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityBenefitPrice extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_benefit_id", nullable = false)
    private CommunityBenefit communityBenefit;

    @Column(nullable = false)
    private Integer headcount;

    @Column(name = "youth_discount", nullable = true)
    private Integer youthDiscount;

    @Column(name = "mobile_discount", nullable = true)
    private Integer mobileDiscount;

    @Column(name = "internet_discount", nullable = true)
    private Integer internetDiscount;
}
