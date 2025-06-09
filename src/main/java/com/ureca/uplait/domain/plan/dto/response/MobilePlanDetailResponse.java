package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "모바일 요금제 상세")
public class MobilePlanDetailResponse extends PlanDetailResponse {

    @Schema(description = "데이터", example = "무제한")
    private String data;

    @Schema(description = "공유 데이터", example = "테더링+쉐어링 80GB")
    private String sharedData;

    @Schema(description = "음성통화", example = "집/이동전화 무제한")
    private String voiceCall;

    @Schema(description = "문자메시지", example = "기본제공")
    private String message;

    @Schema(description = "추가 데이터", example = "월 500MB")
    private String extraData;

    @Schema(name = "미디어 혜택", example = "밀")
    private boolean mediaBenefit;

    @Schema(description = "선택 약정 할인", example = "25")
    private int durationDiscountRate;

    @Schema(description = "프리미엄 요금제 약정 할인", example = "5250")
    private int premierDiscountRate;

    public MobilePlanDetailResponse(MobilePlan plan) {
        super(plan);
        this.data = plan.getData();
        this.sharedData = plan.getSharedData();
        this.voiceCall = plan.getVoiceCall();
        this.message = plan.getMessage();
        this.extraData = plan.getExtraData();
        this.mediaBenefit = plan.getMediaBenefit();
        this.durationDiscountRate = plan.getDurationDiscountRate();
        this.premierDiscountRate = plan.getPremierDiscountRate();
    }
}
