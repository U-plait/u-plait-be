package com.ureca.uplait.domain.mypage.dto;

import com.ureca.uplait.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "마이페이지 수정 요청")
public class MyPageUpdateRequest {
    private String phoneNumber;
    private String email;
    private boolean adAgree;

    public static MyPageUpdateRequest from(User user) {
        return new MyPageUpdateRequest(
                user.getPhoneNumber()
                , user.getEmail()
                , user.getAdAgree()
        );
    }
}
