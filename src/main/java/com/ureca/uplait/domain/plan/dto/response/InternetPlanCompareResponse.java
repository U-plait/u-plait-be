package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.InternetPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
@Schema(description = "인터넷 요금제 상세")
public class InternetPlanCompareResponse extends PlanCompareResponse {

    @Schema(description = "인터넷 속도", example = "500M")
    private String velocity;

    @Schema(description = "인터넷 온라인 할인 가격", example = "42900")
    private int internetDiscount;

    @Schema(description = "태그", example = "무제한")
    private List<TagResponse> tagList;

    @Schema(description = "결합 혜택", example = "가족결합")
    private List<CommunityBenefitResponse> communityBenefitList;

    public InternetPlanCompareResponse(InternetPlan plan) {
        super(plan);
        this.setPlanType("InternetPlan");
        this.velocity = plan.getVelocity();
        this.internetDiscount = plan.getInternetDiscountRate();
    }

}
