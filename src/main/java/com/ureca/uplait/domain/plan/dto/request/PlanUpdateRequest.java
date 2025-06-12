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

    @Schema(description = "요금제 이름", example = "요금제 이름 예시")
    private String planName;

    @Schema(description = "요금제 가격", example = "30000")
    private Integer planPrice;

    @Schema(description = "요금제 혜택 설명", example = "혜택 설명 예시")
    private String planBenefit;

    @Schema(description = "가입 가능 여부", example = "true")
    private Boolean availability;

    @Schema(description = "요금제 설명", example = "선택 설명")
    private String description;
}
