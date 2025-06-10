package com.ureca.uplait.domain.mypage.controller;

import com.ureca.uplait.domain.mypage.dto.response.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.request.MyPageUpdateRequest;
import com.ureca.uplait.domain.mypage.dto.response.MyPageUpdateResponse;
import com.ureca.uplait.domain.mypage.dto.response.MyReviewsResponse;
import com.ureca.uplait.domain.mypage.service.MyPageService;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @Operation(summary = "마이페이지 조회", description = "개인정보 조회: 로그인 필요")
    @GetMapping("user/detail")
    public CommonResponse<MyPageResponse> getMyPage(@AuthenticationPrincipal User user) {

        return CommonResponse.success(myPageService.getMyPage(user));
    }

    @Operation(summary = "마이페이지 개인정보 수정 ", description = "개인정보 수정 후 수정 완료 버튼을 누름: 로그인 필요")
    @PutMapping("user/detail/update")
    public CommonResponse<MyPageUpdateResponse> updateMyPage(@AuthenticationPrincipal User user
            , @RequestBody MyPageUpdateRequest myPageUpdateRequest) {

        return CommonResponse.success(myPageService.updateMyPage(user, myPageUpdateRequest));
    }

    @Operation(summary = "내가 쓴 리뷰 목록", description = "내가 쓴 리뷰 목록 확인 : 로그인 필요")
    @GetMapping("user/detail/review")
    public CommonResponse<List<MyReviewsResponse>> getMyReview(@AuthenticationPrincipal User user) {

        return CommonResponse.success(myPageService.getMyReview(user.getId()));
    }
}
