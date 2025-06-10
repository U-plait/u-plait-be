package com.ureca.uplait.domain.banword.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "금칙어 등록 요청 DTO")
public record BanWordRequest(
        @Schema(description = "등록할 금칙어", example = "시발")
        String banWord) {
}
