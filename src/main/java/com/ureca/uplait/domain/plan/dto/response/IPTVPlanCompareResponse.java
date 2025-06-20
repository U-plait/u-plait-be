package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
@Schema(description = "IPTV 요금제 상세")
public class IPTVPlanCompareResponse extends PlanCompareResponse {

    @Schema(description = "채널 수", example = "213")
    private int channel;

    @Schema(description = "iptv 온라인 할인 가격", example = "13530")
    private int iptvDiscount;


    @Schema(description = "태그", example = "무제한")
    private List<TagResponse> tagList;

    @Schema(description = "결합 혜택", example = "가족결합")
    private List<CommunityBenefitResponse> communityBenefitList;

    public IPTVPlanCompareResponse(IPTVPlan plan) {
        super(plan);
        this.setPlanType("IPTVPlan");
        this.channel = plan.getChannel();
        this.iptvDiscount = plan.getPlanPrice() * (100 - plan.getIptvDiscountRate()) / 100;
    }


}
