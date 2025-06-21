package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByKakaoId(String kakaoId);
	boolean existsByPhoneNumber(String phoneNumber);
	boolean existsByEmail(String email);
	Page<User> findAllByAdAgreeTrue(Pageable pageable);

	@Query(
			value = """
        WITH target_plan_tags AS (
            SELECT tag_id
            FROM plan_tag
            WHERE plan_id = :planId
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
        SELECT u.*
        FROM users u
                 JOIN matched_users mu ON u.id = mu.user_id
        WHERE u.ad_agree = true
        """,
			countQuery = """
        WITH target_plan_tags AS (
            SELECT tag_id
            FROM plan_tag
            WHERE plan_id = :planId
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
        SELECT COUNT(*)
        FROM users u
                 JOIN matched_users mu ON u.id = mu.user_id
        WHERE u.ad_agree = true
        """,
			nativeQuery = true
	)
	Page<User> findUsersWithMatchingTopTagsByPlanId(@Param("planId") Long planId, Pageable pageable);

}
