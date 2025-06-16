package com.ureca.uplait.domain.user.dto.request;

import com.ureca.uplait.domain.user.enums.Gender;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description="회원가입 시 추가정보 담는 DTO")
public class ExtraInfoRequest {
	@Schema(description = "전화번호", example = "010-1234-5678")
	@Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다.")
	String phoneNumber;

	@Schema(description = "이메일 주소", example = "user@google.com")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	String email;

	@Schema(description = "나이", example = "20")
	@Min(value = 0, message = "나이는 0 이상이어야 합니다.")
	@Max(value = 150, message = "나이는 150 이하이어야 합니다.")
	int age;

	@Schema(description = "성별", example = "MALE")
	@NotNull(message = "성별을 입력해주세요.")
	Gender gender;

	@Schema(description = "광고수신동의여부", example = "true")
	boolean adAgree;
}
