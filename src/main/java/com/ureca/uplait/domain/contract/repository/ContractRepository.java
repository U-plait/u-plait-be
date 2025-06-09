package com.ureca.uplait.domain.contract.repository;

import com.ureca.uplait.domain.contract.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    boolean existsByUserIdAndPlanId(Long userId, Long planId);
}
