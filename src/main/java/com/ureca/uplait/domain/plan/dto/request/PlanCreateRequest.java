package com.ureca.uplait.domain.plan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Schema(description = "공통 요금제 생성 요청 DTO")
public abstract class PlanCreateRequest {

    @Schema(description = "요금제 이름", example = "슬기로운 데이터 100G")
    private String planName;

    @Schema(description = "요금제 가격", example = "55000")
    private Integer planPrice;

    @Schema(description = "요금제 혜택 설명", example = "데이터 무제한, 가족결합 할인")
    private String planBenefit;

    @Schema(description = "가입 가능 여부", example = "true")
    private Boolean availability;
}
