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
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, 1003, "사용자를 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 1006, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, 1007, "권한이 없는 사용자입니다."),


    // 2000번대 (로그인 관련)
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 2000, "유효하지 않은 토큰입니다."),
    REFRESH_TOKEN_REQUIRED(HttpStatus.UNAUTHORIZED, 2001, "Refresh Token이 필요합니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, 2002, "Refresh Token이 만료되었습니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, 2003, "Access Token이 만료되었습니다."),
    REISSUE_SUCCESS(HttpStatus.OK, 2004, "토큰 재발급에 성공했습니다. "),
    LOGOUT_SUCCESS(HttpStatus.OK, 2005, "로그아웃에 성공했습니다."),

    // 요금제 3000번대
    PLAN_NOT_FOUND(HttpStatus.NOT_FOUND, 3000, "요금제 정보를 찾을 수 없습니다."),
    INVALID_PLAN(HttpStatus.BAD_REQUEST, 3001, "유효하지 않은 요금제 타입입니다."),
    DUPLICATE_PLAN_NAME(HttpStatus.BAD_REQUEST, 3002, "요금제가 이미 존재합니다."),
    PLAN_DELETE_SUCCESS(HttpStatus.NO_CONTENT, 3003, "요금제가 삭제됐습니다."),

    // 4000번대 (금칙어 관련)
    BANWORD_NOT_FOUND(HttpStatus.NOT_FOUND, 4001, "금칙어를 찾을 수 없습니다."),
    DUPLICATED_BANWORD(HttpStatus.BAD_REQUEST, 4002, "이미 등록된 금칙어입니다."),
    INVALID_BANWORD_INPUT(HttpStatus.BAD_REQUEST, 4003, "금칙어 입력값이 잘못되었습니다."),
    WORD_ALREADY_EXISTS_IN_ALLOW_LIST(HttpStatus.BAD_REQUEST, 4005, "허용어로 이미 등록된 단어입니다."),

    ALLOWWORD_NOT_FOUND(HttpStatus.NOT_FOUND, 4050, "허용어를 찾을 수 없습니다."),
    DUPLICATED_ALLOWWORD(HttpStatus.BAD_REQUEST, 4051, "이미 등록된 허용어입니다."),
    INVALID_ALLOW_WORD(HttpStatus.BAD_REQUEST, 4052, "허용어 입력값이 잘못되었습니다."),
    WORD_ALREADY_EXISTS_IN_BAN_LIST(HttpStatus.BAD_REQUEST, 4053, "금칙어로 이미 등록된 단어입니다."),


    //5000번대
    SIGNUP_SUCCESS(HttpStatus.OK, 5000, "회원가입을 위한 추가정보 입력에 성공했습니다.");


    private final HttpStatus status;
    private final int code;
    private final String message;
}
