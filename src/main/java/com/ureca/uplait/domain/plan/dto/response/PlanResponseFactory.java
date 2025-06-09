package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.global.exception.GlobalException;

import static com.ureca.uplait.global.response.ResultCode.INVALID_PLAN;

public class PlanResponseFactory {
    public static PlanDetailResponse from(Plan plan) {
        if (plan instanceof IPTVPlan iptv) {
            return new IPTVPlanDetailResponse(iptv);
        } else if (plan instanceof InternetPlan internet) {
            return new InternetPlanDetailResponse(internet);
        } else if (plan instanceof MobilePlan mobile) {
            return new MobilePlanDetailResponse(mobile);
        } else {
            throw new GlobalException(INVALID_PLAN);
        }
    }
}

