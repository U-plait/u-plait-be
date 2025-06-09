package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "IPTV 요금제 상세")
public class IPTVPlanDetailResponse extends PlanDetailResponse {

    @Schema(description = "채널 수", example = "213")
    private int channel;

    @Schema(description = "iptv 온라인 할인 가격", example = "13530")
    private int iptvDiscount;

    public IPTVPlanDetailResponse(IPTVPlan plan) {
        super(plan);
        this.channel = plan.getChannel();
        this.iptvDiscount = plan.getIptvDiscountRate();
    }
}
