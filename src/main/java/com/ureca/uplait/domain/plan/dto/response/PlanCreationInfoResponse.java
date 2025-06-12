package com.ureca.uplait.domain.plan.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlanCreationInfoResponse {
    @Schema(description = "태그 List", example = "태그 List")
    List<TagResponse> tagList;
    @Schema(description = "결합 혜택 List", example = "결합 혜택 List")
    List<CommunityBenefitResponse> communityBenefitList;
}
