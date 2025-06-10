package com.ureca.uplait.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ureca.uplait.domain.user.dto.response.DuplicateResponse;
import com.ureca.uplait.domain.user.dto.request.ExtraInfoRequest;
import com.ureca.uplait.domain.user.dto.response.UserNameResponse;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.service.UserService;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/me")
	@Operation(description="네비게이션바 API", summary="로그인한 유저의 이름을 반환한다.")
	public ResponseEntity<CommonResponse<UserNameResponse>> navbar(@AuthenticationPrincipal User user) {
		UserNameResponse result = new UserNameResponse(user.getName());
		return ResponseEntity.ok(CommonResponse.success(result));
	}

	@PostMapping("/extra-info")
	@Operation(description="회원가입 API", summary = "최초 카카오 로그인 이후 추가정보를 입력받는다.")
	public CommonResponse<Void> extraInfo(@Valid @RequestBody ExtraInfoRequest request, @AuthenticationPrincipal User user) {
		userService.updateUserExtraInfo(request, user);
		return new CommonResponse<>(ResultCode.SIGNUP_SUCCESS);
	}

	@GetMapping("/duplicate/phone")
	@Operation(description="전화번호 중복검사 API", summary = "추가정보 입력 시 전화번호 중복 검사를 수행한다.")
	public CommonResponse<DuplicateResponse> duplicatePhone(
		@RequestParam("value")
		@Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-xxxx-xxxx 형식이어야 합니다.")
		String phoneNumber) {
		boolean duplicated = userService.isPhoneNumberDuplicated(phoneNumber);
		DuplicateResponse result = new DuplicateResponse(duplicated);
		return CommonResponse.success(result);
	}

	@GetMapping("/duplicate/email")
	@Operation(description="이메일 중복검사 API", summary = "추가정보 입력 시 이메일 중복 검사를 수행한다.")
	public CommonResponse<DuplicateResponse> duplicateEmail(
		@RequestParam("value")
		@Email(message = "올바른 이메일 형식이 아닙니다.")
		String email
	){
		boolean duplicated = userService.isEmailDuplicated(email);
		DuplicateResponse result = new DuplicateResponse(duplicated);
		return CommonResponse.success(result);
	}




}
