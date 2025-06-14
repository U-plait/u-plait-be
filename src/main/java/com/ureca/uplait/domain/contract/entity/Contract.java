package com.ureca.uplait.domain.contract.entity;

import com.ureca.uplait.domain.community.entity.Community;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contract")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = true)
    private Community community;

    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;
}
