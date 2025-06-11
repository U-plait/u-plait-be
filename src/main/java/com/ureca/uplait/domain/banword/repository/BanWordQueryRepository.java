package com.ureca.uplait.domain.banword.repository;

import com.ureca.uplait.domain.banword.entity.BanWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BanWordQueryRepository {
    Page<BanWord> search(String keyword, Pageable pageable);
    Page<BanWord> findAll(Pageable pageable);

}
