package com.ureca.uplait.domain.admin.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "요금제 생성 응답 DTO")
public class AdminPlanCreateResponse {

    @Schema(description = "요금제 ID", example = "1")
    Long planId;
}
