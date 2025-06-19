package com.ureca.uplait.domain.plan.dto.response;

import static com.ureca.uplait.global.response.ResultCode.INVALID_PLAN;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.global.exception.GlobalException;

public class PlanCompareFactory {

    public static PlanCompareResponse from(Plan plan) {
        if (plan instanceof IPTVPlan iptv) {
            return new IPTVPlanCompareResponse(iptv);
        } else if (plan instanceof InternetPlan internet) {
            return new InternetPlanCompareResponse(internet);
        } else if (plan instanceof MobilePlan mobile) {
            return new MobileCompareResponse(mobile);
        } else {
            throw new GlobalException(INVALID_PLAN);
        }
    }

}
