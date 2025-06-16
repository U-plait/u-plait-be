package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPTVPlanRepository extends JpaRepository<MobilePlan, Long> {

    List<IPTVPlan> findAllByOrderByIdDesc();
}
