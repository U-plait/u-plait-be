package com.ureca.uplait.domain.plan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "모바일 요금제 수정 요청 DTO")
public class MobilePlanUpdateRequest extends PlanUpdateRequest {

    @Schema(description = "요금제 이름", example = "U+ 다이렉트 5G 100G")
    private String planName;

    @Schema(description = "요금제 가격", example = "59000")
    private Integer planPrice;

    @Schema(description = "요금제 혜택 설명", example = "매달 100GB 데이터 제공 + 유튜브 프리미엄")
    private String planBenefit;

    @Schema(description = "가입 가능 여부", example = "true")
    private Boolean availability;

    @Schema(description = "요금제 설명 (선택)", example = "청년 전용 요금제")
    private String description;

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

    @Schema(description = "미디어 혜택 여부", example = "true")
    private Boolean mediaBenefit;

    @Schema(description = "약정 할인율 (%)", example = "10")
    private Integer durationDiscountRate;

    @Schema(description = "프리미어 할인율 (%)", example = "5")
    private Integer premierDiscountRate;
}
