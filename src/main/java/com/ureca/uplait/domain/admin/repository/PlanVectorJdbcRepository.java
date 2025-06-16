package com.ureca.uplait.domain.admin.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PlanVectorJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Value("${jwt.vector.collection-id}")
    private String collectionId;

    public void deleteAll() {
        jdbcTemplate.update(
            "DELETE FROM langchain_pg_embedding WHERE collection_id = ?",
            UUID.fromString(collectionId)
        );
    }
}

