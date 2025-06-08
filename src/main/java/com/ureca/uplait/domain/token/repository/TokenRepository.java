package com.ureca.uplait.domain.token.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ureca.uplait.domain.token.entity.Token;
import com.ureca.uplait.domain.user.entity.User;

public interface TokenRepository extends JpaRepository<Token, Long> {
	Optional<Token> findByUser(User user);
	Optional<Token> findByRefreshToken(String refreshToken);
	void deleteByUser(User user);
}
