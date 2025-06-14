package com.ureca.uplait.domain.admin.controller;

import com.ureca.uplait.domain.admin.dto.request.AdminBanWordRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminBanWordResponse;
import com.ureca.uplait.domain.admin.service.AdminBanWordService;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/banword")
@RequiredArgsConstructor
@Tag(name = "금칙어 관리(Admin)", description = "관리자용 금칙어 등록/조회/삭제 API")
public class AdminBanWordController {

    private final AdminBanWordService banWordService;

    @Operation(summary = "금칙어 등록", description = "금칙어 등록 : 관리자 로그인 필요")
    @PostMapping
    public CommonResponse<AdminBanWordResponse> createBanWord(
        @Parameter(description = "등록할 금칙어 요청")
        @Valid @RequestBody AdminBanWordRequest request) {
        AdminBanWordResponse response = banWordService.registerBanWord(request);
        return CommonResponse.success(response);
    }

    @Operation(summary = "금칙어 전체 조회", description = "금칙어 전체 조회 : 관리자 로그인 필요")
    @GetMapping
    public CommonResponse<Page<AdminBanWordResponse>> getAllBanWords(
        @Parameter(hidden = true)
        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AdminBanWordResponse> result = banWordService.getAllBanWords(pageable);
        return CommonResponse.success(result);
    }

    @Operation(summary = "금칙어 단일 삭제", description = "금칙어 단일 삭제 : 관리자 로그인 필요")
    @DeleteMapping("/{banwordId}")
    public CommonResponse<Long> deleteBanWord(
        @Parameter(description = "삭제할 금칙어 ID")
        @PathVariable("banwordId") Long id) {
        Long deletedId = banWordService.deleteBanWordById(id);
        return CommonResponse.success(ResultCode.BANWORD_DELETE_SUCCESS, deletedId);
    }

    @Operation(summary = "금칙어 일괄 삭제", description = "금칙어 일괄 삭제 : 관리자 로그인 필요")
    @DeleteMapping
    public CommonResponse<Long> deleteBanWords(
        @Parameter(description = "삭제할 금칙어 ID 목록")
        @RequestBody List<Long> ids) {
        banWordService.deleteBanWordsByIds(ids);
        return new CommonResponse<>(ResultCode.BANWORD_DELETE_SUCCESS);
    }

    @Operation(summary = "금칙어 검색", description = "금칙어 검색 : 관리자 로그인 필요")
    @GetMapping("/search")
    public CommonResponse<Page<AdminBanWordResponse>> searchBanWords(
        @Parameter(description = "검색할 키워드", required = false)
        @RequestParam(name = "keyword", required = false) String keyword,

        @Parameter(hidden = true)
        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AdminBanWordResponse> result = banWordService.searchBanWords(keyword, pageable);
        return CommonResponse.success(result);
    }
}

