package com.ureca.uplait.domain.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "금칙어 등록 요청 DTO")
public class AdminBanWordRequest {
    @Schema(description = "등록할 금칙어", example = "시발")
    @NotBlank(message = "금칙어는 공백일 수 없습니다.")
    private String banWord;
}
