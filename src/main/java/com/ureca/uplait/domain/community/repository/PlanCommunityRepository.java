package com.ureca.uplait.domain.community.repository;

import com.ureca.uplait.domain.community.entity.PlanCommunity;
import com.ureca.uplait.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanCommunityRepository extends JpaRepository<PlanCommunity, Long> {
    List<PlanCommunity> findAllByPlan(Plan plan);
}
