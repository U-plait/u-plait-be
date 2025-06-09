package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    boolean existsByPlanName(String planName);
}
