package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.user.entity.PlanTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanTagRepository extends JpaRepository<PlanTag, Long> {

    List<PlanTag> findAllByPlan(Plan plan);

    void deleteAllByPlan(Plan plan);
}
