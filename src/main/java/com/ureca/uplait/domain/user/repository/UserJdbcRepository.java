package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserJdbcRepository {
    List<User> findUsersWithMatchingTopTagsByPlanId(Long planId, Pageable pageable);
}
