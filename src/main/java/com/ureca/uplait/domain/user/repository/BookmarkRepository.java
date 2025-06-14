package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.user.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, CustomBookmarkRepository {
    boolean existsBookmarkByPlanId(Long planId);
}
