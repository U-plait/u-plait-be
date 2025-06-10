package com.ureca.uplait.domain.plan.dto.request;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "모바일 요금제 생성 요청 DTO")
public class MobilePlanCreateRequest extends PlanCreateRequest {

    @Schema(description = "기본 데이터 용량", example = "100GB")
    private String data;

    @Schema(description = "공유 데이터 용량", example = "20GB")
    private String sharedData;

    @Schema(description = "음성 통화 정보", example = "무제한")
    private String voiceCall;

    @Schema(description = "문자 제공량", example = "기본제공")
    private String message;

    @Schema(description = "추가 데이터 정보", example = "매일 2GB + 3Mbps")
    private String extraData;

    @Schema(description = "미디어 혜택 제공 여부", example = "true")
    private Boolean mediaBenefit;

    @Schema(description = "약정 할인율", example = "25")
    private Integer durationDiscountRate;

    @Schema(description = "프리미어 할인율", example = "10")
    private Integer premierDiscountRate;

    public MobilePlan toMobile() {
        return MobilePlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
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
