package com.ureca.uplait.domain.banword.repository;

import com.ureca.uplait.domain.banword.entity.BanWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanWordRepository extends JpaRepository<BanWord, Long> {
    boolean existsByBanWord(String banWord);
}
