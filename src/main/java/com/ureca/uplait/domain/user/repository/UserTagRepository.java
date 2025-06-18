package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.user.entity.Tag;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {

    boolean existsByUserAndTag(User user, Tag tag);
}
