package com.ureca.uplait.domain.plan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "요금제 비교 요청 DTO")
public class PlanCompareRequest {

    @Schema(description = "비교할 요금제의 ID 리스트", example = "[1, 2, 3]")
    private List<Long> planIds;

}