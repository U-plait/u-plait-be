package com.ureca.uplait.domain.auth.dto;

import lombok.Getter;

@Getter
public class KakaoUserRes {
	private Long id;
	private KakaoAccount kakao_account;

	@Getter
	public static class KakaoAccount {
		private String name;
	}

}
