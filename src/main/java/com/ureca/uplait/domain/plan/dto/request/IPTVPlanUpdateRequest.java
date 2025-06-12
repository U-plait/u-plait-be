package com.ureca.uplait.domain.plan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "IPTV 요금제 수정 요청 DTO")
public class IPTVPlanUpdateRequest extends PlanUpdateRequest {

    @Schema(description = "채널 수", example = "200")
    private Integer channel;

    @Schema(description = "IPTV 할인율 (%)", example = "5")
    private Integer iptvDiscountRate;
}
