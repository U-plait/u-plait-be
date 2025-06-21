package com.ureca.uplait.domain.user.repository;

import com.ureca.uplait.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserJdbcRepository{
	Optional<User> findByKakaoId(String kakaoId);
	boolean existsByPhoneNumber(String phoneNumber);
	boolean existsByEmail(String email);
	Page<User> findAllByAdAgreeTrue(Pageable pageable);
}
