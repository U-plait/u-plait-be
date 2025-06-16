package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPTVPlanRepository extends JpaRepository<IPTVPlan, Long> {

    List<IPTVPlan> findAllByOrderByIdDesc();
}
