package com.ureca.uplait.domain.allowword.repository;

import com.ureca.uplait.domain.allowword.entity.AllowWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllowWordRepository extends JpaRepository<AllowWord, Long> {
    boolean existsByAllowWord(String allowWord);
    Page<AllowWord> findByAllowWordContainingIgnoreCase(String keyword, Pageable pageable);
}
