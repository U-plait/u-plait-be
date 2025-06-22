package com.ureca.uplait.domain.plan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ureca.uplait.domain.plan.entity.Plan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "요금제 상세 조회 결과")
public abstract class PlanDetailResponse {

    @Schema(description = "요금제 id", example = "1")
    protected Long planId;

    @Schema(description = "요금제 이름", example = "5G 프리미어 에센셜")
    protected String planName;

    @Schema(description = "요금제 가격", example = "59000")
    protected Integer planPrice;

    @Schema(description = "요금제 혜택", example = "U+ 모바일 TV 기본 월정액 무료")
    protected String planBenefit;

    @Schema(description = "등록 가능 여부", example = "true")
    protected Boolean availability;

    @Schema(description = "요금제 사용 여부", example = "true")
    protected Boolean inUse;

    @Schema(description = "즐겨찾기 여부", example = "true")
    protected Boolean isBookmarked;

    @Schema(description = "플랜 타입", example = "MobilePlan")
    private String planType;

    @Schema(description = "플랜 설명", example = "너무 좋은 요금제")
    private String description;

    @Schema(description = "요금제의 결합 상품", example = "1, 2, 3")
    private List<Long> communityIdList;


    protected PlanDetailResponse(Plan plan, List<Long> communityIdList, boolean inUse) {
        this.planId = plan.getId();
        this.planName = plan.getPlanName();
        this.planPrice = plan.getPlanPrice();
        this.planBenefit = plan.getPlanBenefit();
        this.availability = plan.getAvailability();
        this.description = plan.getDescription();
        this.communityIdList = communityIdList;
        this.inUse = inUse;
    }

    protected PlanDetailResponse(boolean isBookmarked, Plan plan) {
        this.planId = plan.getId();
        this.planName = plan.getPlanName();
        this.planPrice = plan.getPlanPrice();
        this.planBenefit = plan.getPlanBenefit();
        this.availability = plan.getAvailability();
        this.description = plan.getDescription();
        this.isBookmarked = isBookmarked;
    }

    protected void setPlanType(String planType) {
        this.planType = planType;
    }
}
