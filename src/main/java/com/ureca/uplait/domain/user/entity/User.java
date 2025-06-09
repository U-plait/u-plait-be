package com.ureca.uplait.domain.user.entity;

import com.ureca.uplait.domain.chat.entity.ChatLog;
import com.ureca.uplait.domain.contract.entity.Contract;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import com.ureca.uplait.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "kakao_id", nullable = false)
    private String kakaoId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "ad_agree", nullable = false)
    private Boolean adAgree;

    public static User createTmpUser(String kakaoId, String name){
        return User.builder()
            .kakaoId(kakaoId)
            .name(name)
            .phoneNumber("000-0000-0000")
            .email(kakaoId+"kakao.com")
            .age(0)
            .gender(Gender.UNKNOWN)
            .role(Role.TMP_USER)
            .status(Status.ACTIVE)
            .adAgree(false)
            .build();
    }
}
