package com.ureca.uplait.domain.community.entity;


import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "community")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_benefit_id", nullable = false)
    private CommunityBenefit communityBenefit;
}
