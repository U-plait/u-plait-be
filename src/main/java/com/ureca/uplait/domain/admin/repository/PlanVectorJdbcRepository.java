package com.ureca.uplait.domain.admin.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlanVectorJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM plan_vector");
    }
}
