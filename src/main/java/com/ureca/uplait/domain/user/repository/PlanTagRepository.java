package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.user.entity.PlanTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanTagRepository extends JpaRepository<PlanTag, Long> {
    List<PlanTag> findAllByPlan(Plan plan);
}
