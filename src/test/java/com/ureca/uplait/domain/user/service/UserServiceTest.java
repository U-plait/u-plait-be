package com.ureca.uplait.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.ureca.uplait.domain.user.dto.request.ExtraInfoRequest;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import com.ureca.uplait.domain.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
            .name("테스트 사용자")
            .kakaoId("testKakaoId")
            .phoneNumber("010-1111-2222")
            .email("initial@example.com")
            .age(25)
            .gender(Gender.MALE)
            .role(Role.TMP_USER)
            .status(Status.ACTIVE)
            .adAgree(false)
            .build();
    }

    @Test
    @DisplayName("사용자 추가 정보 업데이트 성공")
    void updateUserExtraInfo_success() {
        given(userRepository.findById(testUser.getId())).willReturn(Optional.of(testUser));

        ExtraInfoRequest request = new ExtraInfoRequest();
        request.setPhoneNumber("010-9876-5432");
        request.setEmail("new.email@example.com");
        request.setAge(30);
        request.setGender(Gender.FEMALE);
        request.setAdAgree(true);

        userService.updateUserExtraInfo(request, testUser);

        verify(userRepository, times(1)).findById(testUser.getId());
        assertThat(testUser.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
        assertThat(testUser.getEmail()).isEqualTo(request.getEmail());
        assertThat(testUser.getAge()).isEqualTo(request.getAge());
        assertThat(testUser.getGender()).isEqualTo(request.getGender());
        assertThat(testUser.getAdAgree()).isEqualTo(request.isAdAgree());
        assertThat(testUser.getRole()).isEqualTo(Role.USER);
    }

    @Test
    @DisplayName("전화번호 중복 - x")
    void isPhoneNumberDuplicated_returnsFalse() {
        String uniquePhoneNumber = "010-1234-5678";
        given(userRepository.existsByPhoneNumber(uniquePhoneNumber)).willReturn(false);

        boolean isDuplicated = userService.isPhoneNumberDuplicated(uniquePhoneNumber);

        assertThat(isDuplicated).isFalse();
        verify(userRepository, times(1)).existsByPhoneNumber(uniquePhoneNumber);
    }

    @Test
    @DisplayName("전화번호 중복 - o")
    void isPhoneNumberDuplicated_returnsTrue() {
        String duplicatedPhoneNumber = "010-9876-5432";
        given(userRepository.existsByPhoneNumber(duplicatedPhoneNumber)).willReturn(true);

        boolean isDuplicated = userService.isPhoneNumberDuplicated(duplicatedPhoneNumber);

        assertThat(isDuplicated).isTrue();
        verify(userRepository, times(1)).existsByPhoneNumber(duplicatedPhoneNumber);
    }

    @Test
    @DisplayName("이메일 중복 - x")
    void isEmailDuplicated_returnsFalse() {
        String uniqueEmail = "unique@example.com";
        given(userRepository.existsByEmail(uniqueEmail)).willReturn(false);

        boolean isDuplicated = userService.isEmailDuplicated(uniqueEmail);

        assertThat(isDuplicated).isFalse();
        verify(userRepository, times(1)).existsByEmail(uniqueEmail);
    }

    @Test
    @DisplayName("이메일 중복 - o")
    void isEmailDuplicated_returnsTrue() {
        String duplicatedEmail = "duplicated@example.com";
        given(userRepository.existsByEmail(duplicatedEmail)).willReturn(true);

        boolean isDuplicated = userService.isEmailDuplicated(duplicatedEmail);

        assertThat(isDuplicated).isTrue();
        verify(userRepository, times(1)).existsByEmail(duplicatedEmail);
    }
}