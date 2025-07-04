package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.user.entity.Bookmark;
import java.util.Optional;

import com.ureca.uplait.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>,
    CustomBookmarkRepository {

    boolean existsBookmarkByPlanIdAndUser(Long planId, User user);

    Optional<Bookmark> findByUserIdAndPlanId(Long userId, Long planId);

    Long plan(Plan plan);
}
