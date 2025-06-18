package com.ureca.uplait.domain.user.service;

import com.ureca.uplait.domain.user.dto.request.AddTagRequest;
import com.ureca.uplait.domain.user.entity.Tag;
import com.ureca.uplait.domain.user.entity.UserTag;
import com.ureca.uplait.domain.user.repository.TagRepository;
import com.ureca.uplait.domain.user.repository.UserTagRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ureca.uplait.domain.user.dto.request.ExtraInfoRequest;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserTagRepository userTagRepository;
	private final TagRepository tagRepository;

	@Transactional
	public void updateUserExtraInfo(ExtraInfoRequest request, User detacheduser) {
		User user = userRepository.findById(detacheduser.getId())
				.orElseThrow(() -> new GlobalException(ResultCode.NOT_FOUND_USER));

		user.updateExtraInfo(
			request.getPhoneNumber(),
			request.getEmail(),
			request.getAge(),
			request.getGender(),
			request.isAdAgree()
		);
	}

	@Transactional
	public void addUserTag(AddTagRequest request, User user) {
		for(Long tagId : request.getTagIds()) {
			Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new GlobalException(ResultCode.TAG_NOT_FOUND));

			if (!userTagRepository.existsByUserAndTag(user, tag)) {
				userTagRepository.save(new UserTag(user, tag, 1));
			}
		}
	}

	public boolean isPhoneNumberDuplicated(
		@Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-xxxx-xxxx 형식이어야 합니다.") String phoneNumber) {
		return userRepository.existsByPhoneNumber(phoneNumber);
	}

	public boolean isEmailDuplicated(@Email(message = "올바른 이메일 형식이 아닙니다.") String email) {
		return userRepository.existsByEmail(email);
	}
}
