package com.ureca.uplait.domain.user.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
            .name("테스트이름")
            .kakaoId("testKakaoId")
            .phoneNumber("010-0000-0000")
            .email("test@example.com")
            .age(20)
            .gender(Gender.MALE)
            .role(Role.USER)
            .status(Status.ACTIVE)
            .adAgree(false)
            .build();
    }

    @Test
    @DisplayName("임시 사용자 생성 성공")
    void createTmpUser_success() {
        //given
        String testKakaoId = "z";
        String testName = "안녕하세요~";

        //when
        User tmpUser = User.createTmpUser(testKakaoId, testName);

        //then
        assertThat(tmpUser.getKakaoId()).isEqualTo(testKakaoId);
        assertThat(tmpUser.getName()).isEqualTo(testName);
        assertThat(tmpUser.getPhoneNumber()).isEqualTo("000-0000-0000");
        assertThat(tmpUser.getEmail()).isEqualTo(testKakaoId + "kakao.com");
        assertThat(tmpUser.getAge()).isEqualTo(0);
        assertThat(tmpUser.getGender()).isEqualTo(Gender.UNKNOWN);
        assertThat(tmpUser.getRole()).isEqualTo(Role.TMP_USER);
        assertThat(tmpUser.getStatus()).isEqualTo(Status.ACTIVE);
        assertThat(tmpUser.getAdAgree()).isFalse();
    }

    @Test
    @DisplayName("사용자 기본 정보 업데이트 성공")
    void updateUser_success() {
        //given
        int oldAge = user.getAge();
        Gender oldGender = user.getGender();
        Role oldRole = user.getRole();
        String oldName = user.getName();
        String oldKakaoId = user.getKakaoId();

        //when
        String updatedPhoneNumber = "010-9999-8888";
        String updatedEmail = "새로운@naver.com";
        boolean updatedAdAgree = true;

        user.updateUser(updatedPhoneNumber, updatedEmail, updatedAdAgree);

        assertThat(user.getPhoneNumber()).isEqualTo(updatedPhoneNumber);
        assertThat(user.getEmail()).isEqualTo(updatedEmail);
        assertThat(user.getAdAgree()).isEqualTo(updatedAdAgree);

        //then
        assertThat(user.getAge()).isEqualTo(oldAge);
        assertThat(user.getGender()).isEqualTo(oldGender);
        assertThat(user.getRole()).isEqualTo(oldRole);
        assertThat(user.getName()).isEqualTo(oldName);
        assertThat(user.getKakaoId()).isEqualTo(oldKakaoId);
    }

}