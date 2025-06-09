package com.ureca.uplait.domain.community.entity;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plan_community")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCommunity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_benefit_id", nullable = false)
    private CommunityBenefit communityBenefit;

}
