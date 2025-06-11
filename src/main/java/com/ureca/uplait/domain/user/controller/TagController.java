package com.ureca.uplait.domain.user.controller;

import com.ureca.uplait.domain.user.dto.response.TagListResponse;
import com.ureca.uplait.domain.user.service.TagService;
import com.ureca.uplait.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tag")
@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    /**
     *  태그 전체 조회
     */
    @Operation(summary = "태그 전체 조회", description = "태그 전체 조회")
    @GetMapping
    public CommonResponse<TagListResponse> getTags() {
        return CommonResponse.success(tagService.getTags());
    }
}
