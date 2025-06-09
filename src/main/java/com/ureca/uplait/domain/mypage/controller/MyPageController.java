package com.ureca.uplait.domain.mypage.controller;

import com.ureca.uplait.domain.mypage.dto.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.MyReviewsResponse;
import com.ureca.uplait.domain.mypage.dto.ReviewSummaryResponse;
import com.ureca.uplait.domain.mypage.service.MyPageService;
import com.ureca.uplait.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @Operation(summary = "마이페이지 조회", description = "개인정보 조회 및 수정? 수정은 잘 모르겠다: 로그인 필요")
    @GetMapping("user/detail")
    public ResponseEntity<MyPageResponse> getMyPage(@AuthenticationPrincipal User user) {

        MyPageResponse myPageResponse = myPageService.getMyPage(user.getId());
        return ResponseEntity.ok(myPageResponse);
    }

    @Operation(summary = "내가 쓴 리뷰 목록", description = "내가 쓴 리뷰 목록 확인 : 로그인 필요")
    @GetMapping("user/detail/review")
    public ResponseEntity<MyReviewsResponse> getMyReview(@AuthenticationPrincipal User user) {

        MyReviewsResponse reviewsResponse = myPageService.getMyReview(user.getId());
        return ResponseEntity.ok(reviewsResponse);
    }
}
