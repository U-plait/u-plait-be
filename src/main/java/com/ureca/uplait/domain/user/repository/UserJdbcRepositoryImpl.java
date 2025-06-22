package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserJdbcRepositoryImpl implements UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findUsersWithMatchingTopTagsByPlanId(Long planId, Pageable pageable) {
        String sql = """
        WITH target_plan_tags AS (
            SELECT tag_id
            FROM plan_tag
            WHERE plan_id = ?
        ),
        user_top_tags AS (
            SELECT
                user_id,
                tag_id,
                tag_count,
                RANK() OVER (PARTITION BY user_id ORDER BY tag_count DESC) AS rank
            FROM user_tag
        ),
        user_top2_tags AS (
            SELECT *
            FROM user_top_tags
            WHERE rank <= 2
        ),
        matched_users AS (
            SELECT DISTINCT ut.user_id
            FROM user_top2_tags ut
                     JOIN target_plan_tags tp ON ut.tag_id = tp.tag_id
        )
        SELECT *
        FROM users u
                 JOIN matched_users mu ON u.id = mu.user_id
        WHERE u.ad_agree = true
        ORDER BY u.id
        LIMIT ? OFFSET ?
    """;

        return jdbcTemplate.query(
                sql,
                new UserRowMapper(),
                planId,
                pageable.getPageSize(),
                pageable.getOffset()
        );
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return User.builder()
                    .name(rs.getString("name"))
                    .kakaoId(rs.getString("kakao_id"))
                    .phoneNumber(rs.getString("phone_number"))
                    .email(rs.getString("email"))
                    .age(rs.getInt("age"))
                    .gender(Gender.valueOf(rs.getString("gender")))
                    .role(Role.valueOf(rs.getString("role")))
                    .status(Status.valueOf(rs.getString("status")))
                    .adAgree(rs.getBoolean("ad_agree"))
                    .build();
        }
    }
}
