package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserJdbcRepository {
    List<User> findUsersWithMatchingTopTagsByPlanId(Long planId, Pageable pageable);
    long countUsersWithMatchingTopTagsByPlanId(Long planId);
}
