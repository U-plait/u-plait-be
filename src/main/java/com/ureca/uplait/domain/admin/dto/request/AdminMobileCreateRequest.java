package com.ureca.uplait.domain.admin.dto.request;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.MediaBenefit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminMobileCreateRequest extends PlanCommonRequest {
    @Schema(description = "제공 데이터 용량", example = "100GB")
    private String data;

    @Schema(description = "공유 가능한 데이터 용량", example = "20GB")
    private String sharedData;

    @Schema(description = "음성통화 정보", example = "무제한")
    private String voiceCall;

    @Schema(description = "문자 정보", example = "무제한")
    private String message;

    @Schema(description = "추가 데이터 정보", example = "매달 1GB 추가 제공")
    private String extraData;

    @Schema(description = "미디어 혜택 여부", example = "NORMAL")
    private MediaBenefit mediaBenefit;

    @Schema(description = "약정 할인율 (%)", example = "10")
    private Integer durationDiscountRate;

    @Schema(description = "프리미어 할인율 (%)", example = "5")
    private Integer premierDiscountRate;

    public MobilePlan toMobile() {
        return MobilePlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
            .description(getDescription())
            .data(data)
            .sharedData(sharedData)
            .voiceCall(voiceCall)
            .message(message)
            .extraData(extraData)
            .mediaBenefit(mediaBenefit)
            .durationDiscountRate(durationDiscountRate)
            .premierDiscountRate(premierDiscountRate)
            .build();
    }

}
