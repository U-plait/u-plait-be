package com.ureca.uplait.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ureca.uplait.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByKakaoId(String kakaoId);
	boolean existsByPhoneNumber(String phoneNumber);
	boolean existsByEmail(String email);
}
