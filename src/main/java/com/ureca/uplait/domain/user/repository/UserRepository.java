package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByKakaoId(String kakaoId);
	boolean existsByPhoneNumber(String phoneNumber);
	boolean existsByEmail(String email);
	Page<User> findAllByAdAgreeTrue(Pageable pageable);
}
