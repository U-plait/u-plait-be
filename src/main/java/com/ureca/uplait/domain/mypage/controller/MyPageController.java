package com.ureca.uplait.domain.mypage.controller;

import com.ureca.uplait.domain.mypage.dto.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.MyPageUpdateRequest;
import com.ureca.uplait.domain.mypage.dto.MyPageUpdateResponse;
import com.ureca.uplait.domain.mypage.dto.MyReviewsResponse;
import com.ureca.uplait.domain.mypage.service.MyPageService;
import com.ureca.uplait.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @Operation(summary = "마이페이지 조회", description = "개인정보 조회: 로그인 필요")
    @GetMapping("user/detail")
    public ResponseEntity<MyPageResponse> getMyPage(@AuthenticationPrincipal User user) {

        MyPageResponse myPageResponse = myPageService.getMyPage(user.getId());
        return ResponseEntity.ok(myPageResponse);
    }

    @Operation(summary = "마이페이지 개인정보 수정 ", description = "개인정보 수정 후 수정 완료 버튼을 누름: 로그인 필요")
    @PutMapping("user/detail/update")
    public ResponseEntity<MyPageUpdateResponse> updateMyPage(@AuthenticationPrincipal User user
            , @RequestBody MyPageUpdateRequest myPageUpdateRequest) {

        MyPageUpdateResponse myPageUpdateResponse = myPageService.updateMyPage(myPageUpdateRequest);
        return ResponseEntity.ok(new MyPageUpdateResponse("회원 정보가 성공적으로 수정되었습니다."));
    }

    @Operation(summary = "내가 쓴 리뷰 목록", description = "내가 쓴 리뷰 목록 확인 : 로그인 필요")
    @GetMapping("user/detail/review")
    public ResponseEntity<List<MyReviewsResponse>> getMyReview(@AuthenticationPrincipal User user) {

        List<MyReviewsResponse> reviewsResponse = myPageService.getMyReview(user.getId());
        return ResponseEntity.ok(reviewsResponse);
    }
}
