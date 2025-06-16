package com.ureca.uplait.domain.admin.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "요금제 삭제 응답 DTO")
public class AdminPlanDeleteResponse {
    @Schema(description = "삭제된 요금제 ID", example = "1")
    Long PlanId;
}
