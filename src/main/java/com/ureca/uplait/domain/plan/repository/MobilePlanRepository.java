package com.ureca.uplait.domain.plan.repository;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobilePlanRepository extends JpaRepository<MobilePlan, Long> {

    List<MobilePlan> findAllByOrderByIdDesc();
}
