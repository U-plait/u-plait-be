package com.ureca.uplait.domain.admin.controller;

import com.ureca.uplait.domain.admin.dto.request.AdminAllowWordRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminAllowWordResponse;
import com.ureca.uplait.domain.admin.service.AdminAllowWordService;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/allowword")
@RequiredArgsConstructor
@Tag(name = "허용어 관리(Admin)", description = "관리자용 허용어 등록/조회/삭제/검색 API")
public class AdminAllowWordController {

    private final AdminAllowWordService allowWordService;

    @Operation(summary = "허용어 등록", description = "허용어 등록 : 관리자 로그인 필요")
    @PostMapping
    public CommonResponse<AdminAllowWordResponse> createAllowWord(
            @Parameter(description = "등록할 허용어 요청")
            @Valid @RequestBody AdminAllowWordRequest request) {
        AdminAllowWordResponse response = allowWordService.registerAllowWord(request);
        return CommonResponse.success(response);
    }

    @Operation(summary = "허용어 전체 조회", description = "허용어 전체 조회 : 관리자 로그인 필요")
    @GetMapping
    public CommonResponse<Page<AdminAllowWordResponse>> getAllBanWords(
            @Parameter(hidden = true)
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AdminAllowWordResponse> result= allowWordService.getAllAllowWords(pageable);
        return CommonResponse.success(result);
    }

    @Operation(summary = "허용어 단일 삭제", description = "허용어 단일 삭제 : 관리자 로그인 필요")
    @DeleteMapping("/{allowwordId}")
    public CommonResponse<Void> deleteAllowWord(
            @Parameter(description = "삭제할 허용어 ID")
            @PathVariable("allowwordId") Long id) {
        allowWordService.deleteAllowWordById(id);
        return new CommonResponse<>(ResultCode.SUCCESS);
    }

    @Operation(summary = "허용어 일괄 삭제", description = "허용어 일괄 삭제 : 관리자 로그인 필요")
    @DeleteMapping
    public CommonResponse<Void> deleteAllowWords(
            @Parameter(description = "삭제할 허용어 ID 목록")
            @RequestBody List<Long> ids) {
        allowWordService.deleteAllowWordsByIds(ids);
        return new CommonResponse<>(ResultCode.SUCCESS);
    }

    @Operation(summary = "허용어 검색", description = "허용어 검색 : 관리자 로그인 필요")
    @GetMapping("/search")
    public CommonResponse<Page<AdminAllowWordResponse>> searchAllowWords(
            @Parameter(description = "검색할 키워드", required = false)
            @RequestParam(name = "keyword", required = false) String keyword,

            @Parameter(hidden = true)
            @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AdminAllowWordResponse> result = allowWordService.searchAllowWords(keyword, pageable);
        return CommonResponse.success(result);
    }
}
