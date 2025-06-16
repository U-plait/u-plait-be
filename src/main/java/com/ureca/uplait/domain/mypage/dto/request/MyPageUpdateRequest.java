package com.ureca.uplait.domain.mypage.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "마이페이지 수정 요청")
public class MyPageUpdateRequest {
    @Schema(description = "핸드폰 번호", example = "010-1234-5678")
    private String phoneNumber;
    @Schema(description = "이메일", example = "ureca@kakao.com")
    private String email;
    @Schema(description = "광고 수신 동의", example = "true")
    private boolean adAgree;
}
