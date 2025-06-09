package com.ureca.uplait.domain.mypage.controller;

import com.ureca.uplait.domain.mypage.dto.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.UserProfileResponse;
import com.ureca.uplait.domain.mypage.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
public class UserController {
    private MyPageService myPageService;

    @Operation(summary = "마이페이지 조회", description = "개인정보 조회 및 내가 쓴 리뷰 조회: 로그인 필요")
    @GetMapping("user/{userId}/detail")
    public ResponseEntity<MyPageResponse> getMyPage(
            @PathVariable Long userId, UserProfileResponse userProfile) {

//        if(!userProfile.getUserId().equals(userId)) {
//            throw new AccessDeniedException("본인의 마이페이지에만 접근할 수 있습니다.");
//        }

        MyPageResponse myPageResponse = myPageService.getMyPage(userId);
        return ResponseEntity.ok(myPageResponse);
    }
}
