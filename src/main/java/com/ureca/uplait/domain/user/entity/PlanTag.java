package com.ureca.uplait.domain.user.entity;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plan_tag")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanTag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;
}