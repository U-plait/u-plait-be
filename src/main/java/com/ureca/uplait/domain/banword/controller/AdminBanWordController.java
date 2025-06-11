package com.ureca.uplait.domain.banword.controller;

import com.ureca.uplait.domain.banword.dto.request.BanWordRequest;
import com.ureca.uplait.domain.banword.dto.response.BanWordResponse;
import com.ureca.uplait.domain.banword.service.BanWordService;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/banword")
@RequiredArgsConstructor
@Tag(name = "금칙어 관리(Admin)", description = "관리자용 금칙어 등록/조회/삭제 API")
public class AdminBanWordController {

    private final BanWordService banWordService;

    // 금칙어 생성
    @Operation(summary = "금칙어 등록", description = "금칙어 등록 : 관리자 로그인 필요")
    @PostMapping
    public ResponseEntity<CommonResponse<BanWordResponse>> createBanWord(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "등록할 금칙어 요청")
            @RequestBody BanWordRequest request) {
        BanWordResponse response = banWordService.registerBanWord(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    // 금칙어 전체 조회
    @Operation(summary = "금칙어 전체 조회", description = "금칙어 전체 조회 : 관리자 로그인 필요")
    @GetMapping
    public ResponseEntity<CommonResponse<List<BanWordResponse>>> getAllBanWords() {
        List<BanWordResponse> list = banWordService.getAllBanWords();
        return ResponseEntity.ok(CommonResponse.success(list));
    }


    // 금칙어 단일 삭제
    @Operation(summary = "금칙어 단일 삭제", description = "금칙어 단일 삭제 : 관리자 로그인 필요")
    @DeleteMapping("/{banwordId}")
    public ResponseEntity<CommonResponse<Void>> deleteBanWord(
            @Parameter(description = "삭제할 금칙어 ID")
            @PathVariable("banwordId") Long id) {
        banWordService.deleteBanWordById(id);
        return ResponseEntity.ok(new CommonResponse<>(ResultCode.SUCCESS));
    }


    // 금칙어 일괄 삭제
    @Operation(summary = "금칙어 일괄 삭제", description = "금칙어 일괄 삭제 : 관리자 로그인 필요")
    @DeleteMapping
    public ResponseEntity<CommonResponse<Void>> deleteBanWords(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "삭제할 금칙어 ID 목록")
            @RequestBody List<Long> ids) {
        banWordService.deleteBanWordsByIds(ids);
        return ResponseEntity.ok(new CommonResponse<>(ResultCode.SUCCESS));
    }

    // 금칙어 검색
    @Operation(summary = "금칙어 검색", description = "금칙어 검색 : 관리자 로그인 필요")
    @GetMapping("/search")
    public ResponseEntity<CommonResponse<Page<BanWordResponse>>> searchBanWords(
            @Parameter(description = "검색할 키워드", required = false)
            @RequestParam(name = "keyword", required = false) String keyword,

            @Parameter(hidden = true)
            @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<BanWordResponse> result = banWordService.searchBanWords(keyword, pageable);
        return ResponseEntity.ok(CommonResponse.success(result));
    }
}

