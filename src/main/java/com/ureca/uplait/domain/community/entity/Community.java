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

    @ManyToOne
    @JoinColumn(name = "community_plan_id", nullable = false)
    private CommunityPlan communityPlan;
}
