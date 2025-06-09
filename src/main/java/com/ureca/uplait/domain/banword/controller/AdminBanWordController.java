package com.ureca.uplait.domain.banword.controller;

import com.ureca.uplait.domain.banword.dto.request.BanWordRequest;
import com.ureca.uplait.domain.banword.dto.response.BanWordResponse;
import com.ureca.uplait.domain.banword.service.BanWordService;
import com.ureca.uplait.global.response.CommonResponse;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/banword")
@RequiredArgsConstructor
public class AdminBanWordController {

    private final BanWordService banWordService;

    // 금칙어 생성
    @PostMapping
    public ResponseEntity<CommonResponse<BanWordResponse>> createBanWord(@RequestBody BanWordRequest request) {
        BanWordResponse response = banWordService.registerBanWord(request);
        return ResponseEntity.ok(CommonResponse.success(response));
    }

    // 금칙어 전체 조회
    @GetMapping
    public ResponseEntity<CommonResponse<List<BanWordResponse>>> getAllBanWords() {
        List<BanWordResponse> list = banWordService.getAllBanWords();
        return ResponseEntity.ok(CommonResponse.success(list));
    }


    // 금칙어 단일 삭제
    @DeleteMapping("/{banwordId}")
    public ResponseEntity<CommonResponse<Void>> deleteBanWord(@PathVariable Long id) {
        banWordService.deleteBanWordById(id);
        return ResponseEntity.ok(new CommonResponse<>(ResultCode.SUCCESS));
    }


    // 금칙어 일괄 삭제
    @DeleteMapping
    public ResponseEntity<CommonResponse<Void>> deleteBanWords(@RequestBody List<Long> ids) {
        banWordService.deleteBanWordsByIds(ids);
        return ResponseEntity.ok(new CommonResponse<>(ResultCode.SUCCESS));
    }
}
