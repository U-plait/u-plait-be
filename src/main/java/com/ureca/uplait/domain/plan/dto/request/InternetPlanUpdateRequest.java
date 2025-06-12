package com.ureca.uplait.domain.plan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "인터넷 요금제 수정 요청 DTO")
public class InternetPlanUpdateRequest extends PlanUpdateRequest {

    @Schema(description = "인터넷 속도", example = "1Gbps")
    private String velocity;

    @Schema(description = "인터넷 할인율 (%)", example = "10")
    private Integer internetDiscountRate;
}
