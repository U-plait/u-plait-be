package com.ureca.uplait.domain.user.controller;

import com.ureca.uplait.domain.plan.entity.PlanType;
import com.ureca.uplait.domain.user.dto.response.BookmarkListResponse;
import com.ureca.uplait.domain.user.dto.response.CreateBookmarkResponse;
import com.ureca.uplait.domain.user.dto.response.DeleteBookmarkResponse;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.service.BookmarkService;
import com.ureca.uplait.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bookmark")
@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(summary = "즐겨찾기 조회", description = "즐겨찾기 조회: 로그인 필요")
    @GetMapping
    public CommonResponse<BookmarkListResponse> getBookmarks(
        @Parameter(description = "사용자정보", required = true)
        @AuthenticationPrincipal User user,
        @Parameter(description = "요금제 타입", required = true)
        @RequestParam PlanType planType,
        @Parameter(description = "한 번에 가져올 크기")
        @RequestParam(defaultValue = "5") int size,
        @Parameter(description = "마지막 즐겨찾기 Id")
        @RequestParam(required = false) Long lastBookmarkId) {
        return CommonResponse.success(
            bookmarkService.getBookmarks(user, planType, size, lastBookmarkId));
    }

    @Operation(summary = "즐겨찾기 추가", description = "즐겨찾기 추가: 로그인 필요")
    @PostMapping
    public CommonResponse<CreateBookmarkResponse> createBookmark(
        @Parameter(description = "사용자정보", required = true)
        @AuthenticationPrincipal User user,
        @Parameter(description = "요금제 id", required = true)
        @RequestParam Long planId) {
        return CommonResponse.success(bookmarkService.createBookmark(user, planId));
    }

    @Operation(summary = "즐겨찾기 삭제", description = "즐겨찾기 삭제: 로그인 필요")
    @DeleteMapping
    public CommonResponse<DeleteBookmarkResponse> deleteBookmark(
        @Parameter(description = "사용자정보", required = true)
        @AuthenticationPrincipal User user,
        @Parameter(description = "요금제 id", required = true)
        @RequestParam Long planId) {
        return CommonResponse.success(bookmarkService.deleteBookmark(user, planId));
    }
}
