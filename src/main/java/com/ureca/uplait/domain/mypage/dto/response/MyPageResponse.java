package com.ureca.uplait.domain.mypage.dto.response;

import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "마이페이지 응답")
public class MyPageResponse {
    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;
    @Schema(description = "핸드폰 번호", example = "010-1234-5678")
    private String phoneNumber;
    @Schema(description = "이메일", example = "ureca@kakao.com")
    private String email;
    @Schema(description = "나이", example = "12")
    private int age;
    @Schema(description = "성별", example = "MALE")
    private Gender gender;
    @Schema(description = "광고 수신 동의", example = "true")
    private boolean adAgree;
}
