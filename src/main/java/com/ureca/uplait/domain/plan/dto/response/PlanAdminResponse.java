package com.ureca.uplait.domain.plan.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "관리자용 요금제 응답 DTO")
public abstract class PlanAdminResponse {

    @Schema(description = "요금제 ID", example = "1")
    private Long id;

    @Schema(description = "요금제 이름", example = "스페셜 요금제")
    private String planName;

    @Schema(description = "요금제 가격", example = "35000")
    private Integer planPrice;
}