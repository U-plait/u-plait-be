package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "모바일 요금제 관리자용 응답 DTO")
public class MobilePlanAdminResponse extends PlanAdminResponse {

    public MobilePlanAdminResponse(MobilePlan plan) {
        super(plan.getId(), plan.getPlanName(), plan.getPlanPrice());
    }
}
