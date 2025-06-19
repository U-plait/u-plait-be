package com.ureca.uplait.domain.plan.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlanCreationInfoResponse {

    @Schema(description = "태그 List", example = "태그 List")
    List<TagResponse> tagList;
    @Schema(description = "결합 혜택 List", example = "결합 혜택 List")
    List<CommunityBenefitResponse> communityBenefitList = new ArrayList<>();
}
