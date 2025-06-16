package com.ureca.uplait.domain.mypage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "마이페이지 수정 응답")
public class MyPageUpdateResponse {
    @Schema(description = "user id", example = "1")
    private Long userId;
}
