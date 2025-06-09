package com.ureca.uplait.domain.mypage.dto;

import com.ureca.uplait.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Schema(description = "마이페이지 수정 응답")
public class MyPageUpdateResponse {
    private Long userId;
}
