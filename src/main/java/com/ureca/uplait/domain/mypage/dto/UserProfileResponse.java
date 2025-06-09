package com.ureca.uplait.domain.mypage.dto;

import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponse {
    private Long userId;
    private String name;
    private String kakaoId;
    private String phoneNumber;
    private String email;
    private int age;
    private Gender gender;
    private Role role;
    private Status status;
    private boolean adAgree;

    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(
                user.getId()
                , user.getName()
                , user.getKakaoId()
                , user.getPhoneNumber()
                , user.getEmail()
                , user.getAge()
                , user.getGender()
                , user.getRole()
                , user.getStatus()
                , user. getAdAgree()
        );
    }
}
