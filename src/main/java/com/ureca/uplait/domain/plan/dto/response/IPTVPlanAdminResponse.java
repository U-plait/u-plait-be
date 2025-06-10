package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.Plan;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "IPTV 요금제 관리자용 응답 DTO")
public class IPTVPlanAdminResponse extends PlanAdminResponse {

    public IPTVPlanAdminResponse(Plan plan) {
        super(plan.getId(), plan.getPlanName(), plan.getPlanPrice());
    }
}
