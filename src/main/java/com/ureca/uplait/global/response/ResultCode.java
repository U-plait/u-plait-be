package com.ureca.uplait.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResultCode {
    // 글로벌 1000번대
    SUCCESS(HttpStatus.OK, 0, "정상 처리 되었습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, 1000, "잘못된 입력값입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 1001, "유효하지 않은 토큰입니다."), // 로그인 관련
    REFRESH_TOKEN_REQUIRED(HttpStatus.UNAUTHORIZED, 1002, "Refresh Token이 필요합니다."), // 로그인 관련
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, 1003, "사용자를 찾을 수 없습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, 1004, "Refresh Token이 만료되었습니다."), // 로그인 관련
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, 1005, "Access Token이 만료되었습니다."), // 로그인 관련
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 1006, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 1007, "권한이 없는 사용자입니다."),

    // 2000번대

    ;

    private final HttpStatus status;
    private final int code;
    private final String message;
}
