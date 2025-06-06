package com.ureca.uplait.domain.community.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="community_plan")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityPlan extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "max_phone", nullable = false)
    private Integer maxPhone;

    @Column(name = "max_internet", nullable = false)
    private Integer maxInternet;

    @Column(name = "max_iptv", nullable = false)
    private Integer maxIptv;

    @Column(name = "plan_benefit", nullable = true)
    private String planBenefit;

    @Column(name = "community_condition", nullable = true)
    private String communityCondition;

    @OneToMany(mappedBy = "communityPlan", cascade = CascadeType.ALL)
    private List<CommunityPlanPrice> priceList;

    @OneToMany(mappedBy = "communityPlan", cascade = CascadeType.ALL)
    private List<Community> communities;
}
