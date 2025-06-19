package com.ureca.uplait.domain.community.repository;

import com.ureca.uplait.domain.community.entity.PlanCommunity;
import com.ureca.uplait.domain.plan.entity.Plan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCommunityRepository extends JpaRepository<PlanCommunity, Long> {

    List<PlanCommunity> findAllByPlan(Plan plan);

    void deleteAllByPlan(Plan plan);
}
