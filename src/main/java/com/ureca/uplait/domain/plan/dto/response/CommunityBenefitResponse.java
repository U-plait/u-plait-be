package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.community.entity.CommunityBenefit;
import lombok.Getter;

@Getter
public class CommunityBenefitResponse {

    private Long id;
    private String communityName;

    public CommunityBenefitResponse(CommunityBenefit communityBenefit) {
        this.id = communityBenefit.getId();
        this.communityName = communityBenefit.getName();
    }
}
