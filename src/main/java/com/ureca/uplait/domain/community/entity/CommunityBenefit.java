package com.ureca.uplait.domain.community.entity;

import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="community_benefit")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityBenefit extends BaseEntity {

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
}
