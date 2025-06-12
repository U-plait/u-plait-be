package com.ureca.uplait.domain.banword.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
@AllArgsConstructor
@Schema(description = "금칙어 조회 응답 DTO")
public class BanWordResponse {

    @Schema(description = "금칙어 ID", example = "1")
    private Long id;

    @Schema(description = "금칙어 문자열", example = "시발")
    private String banWord;

    @Schema(description = "등록 일시", example = "2025-06-10T00:00:00")
    private LocalDateTime createdAt;
}
