package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.plan.entity.PlanType;
import com.ureca.uplait.domain.user.entity.Bookmark;

import java.util.List;

public interface CustomBookmarkRepository {
    List<Bookmark> getBookmarksByPlanAndPage(Long user, int size, Long lastBookmarkId, PlanType planType);
}
