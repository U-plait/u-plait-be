package com.ureca.uplait.domain.batch;

import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.UserJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class JdbcPagingUserReader implements ItemReader<User> {

    private final UserJdbcRepository userJdbcRepository;
    private final Long planId;
    private final int pageSize;

    private int currentPage = 0;
    private Iterator<User> currentIterator;

    @Override
    public User read() {
        if (currentIterator == null || !currentIterator.hasNext()) {
            Pageable pageable = PageRequest.of(currentPage++, pageSize);
            List<User> users = userJdbcRepository.findUsersWithMatchingTopTagsByPlanId(planId, pageable);
            if (users.isEmpty()) return null; // 배치 종료 조건
            currentIterator = users.iterator();
        }

        return currentIterator.next();
    }
}
