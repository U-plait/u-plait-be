package com.ureca.uplait.domain.banword.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ureca.uplait.domain.banword.entity.BanWord;
import com.ureca.uplait.domain.banword.entity.QBanWord;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BanWordQueryRepositoryImpl implements BanWordQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BanWord> search(String keyword, Pageable pageable) {
        QBanWord banWord = QBanWord.banWord1;

        BooleanBuilder builder = new BooleanBuilder();
        if (keyword != null && !keyword.isBlank()) {
            builder.and(banWord.banWord.containsIgnoreCase(keyword));
        }

        List<BanWord> content = queryFactory
                .selectFrom(banWord)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long cnt = queryFactory
                .select(banWord.count())
                .from(banWord)
                .where(builder)
                .fetchOne();

        long total = (cnt != null) ? cnt : 0L;

        return new PageImpl<>(content, pageable, total);
    }
}
