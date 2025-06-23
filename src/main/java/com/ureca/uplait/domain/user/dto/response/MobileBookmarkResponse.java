package com.ureca.uplait.domain.user.dto.response;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MobileBookmarkResponse extends BookmarkResponse {
    @Schema(description = "약정 할인 가격", example = "25")
    private int durationDiscountRate;

    @Schema(description = "프리미어 할인 가격", example = "25")
    private int premierDiscountRate;

    @Schema(description = "데이터", example = "128GB")
    private String data;

    @Schema(description = "음성 통화", example = "무제한")
    private String voiceCall;

    @Schema(description = "메시지", example = "무제한")
    private String message;

    public MobileBookmarkResponse(MobilePlan plan, Long bookmarkId, boolean isBookmarked) {
        super(plan, bookmarkId, isBookmarked);
        this.durationDiscountRate = plan.getDurationDiscountRate();
        this.premierDiscountRate = plan.getPremierDiscountRate();
        this.data = plan.getData();
        this.voiceCall = plan.getVoiceCall();
        this.message = plan.getMessage();
    }
}
