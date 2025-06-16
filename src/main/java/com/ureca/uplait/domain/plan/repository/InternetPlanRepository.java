package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.entity.InternetPlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternetPlanRepository extends JpaRepository<InternetPlan, Long> {

    List<InternetPlan> findAllByOrderByIdDesc();
}
