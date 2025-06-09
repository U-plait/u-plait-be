package com.ureca.uplait.domain.mypage.dto;

import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "마이페이지 응답")
public class MyPageResponse {
    //@Schema()
    private String name;
    private String phoneNumber;
    private String email;
    private int age;
    private Gender gender;
    private boolean adAgree;

    public static MyPageResponse from(User user) {
        return new MyPageResponse(
                user.getName()
                , user.getPhoneNumber()
                , user.getEmail()
                , user.getAge()
                , user.getGender()
                , user.getAdAgree()
        );
    }
}
