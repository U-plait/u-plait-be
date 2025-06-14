package com.ureca.uplait.domain.token.entity;

import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="token")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token extends BaseEntity {

	@Column(name="refresh_token", nullable = false, length = 500)
	private String refreshToken;

	@Column(name="expiry_date", nullable = false)
	private LocalDateTime expiryDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false)
	private User user;

	public void updateRefreshToken(String refreshToken, LocalDateTime expiryDate) {
		this.refreshToken = refreshToken;
		this.expiryDate = expiryDate;
	}
}
