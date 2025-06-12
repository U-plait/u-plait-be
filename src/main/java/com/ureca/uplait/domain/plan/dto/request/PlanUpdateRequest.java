package com.ureca.uplait.domain.plan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "공통 요금제 수정 요청 필드")
public abstract class PlanUpdateRequest {

    @Schema(description = "요금제 이름", example = "슬기로운 데이터 100G")
    private String planName;

    @Schema(description = "요금제 가격", example = "55000")
    private Integer planPrice;

    @Schema(description = "요금제 혜택 설명", example = "데이터 무제한, 가족결합 할인")
    private String planBenefit;

    @Schema(description = "가입 가능 여부", example = "true")
    private Boolean availability;

    @Schema(description = "요금제 설명", example = "선택 설명")
    private String description;
}
