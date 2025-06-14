package com.ureca.uplait.domain.plan.dto.response;

import static com.ureca.uplait.global.response.ResultCode.INVALID_PLAN;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.global.exception.GlobalException;

public class PlanResponseFactory {

    public static PlanDetailResponse from(Plan plan, boolean inUse) {
        if (plan instanceof IPTVPlan iptv) {
            return new IPTVPlanDetailResponse(iptv, inUse);
        } else if (plan instanceof InternetPlan internet) {
            return new InternetPlanDetailResponse(internet, inUse);
        } else if (plan instanceof MobilePlan mobile) {
            return new MobilePlanDetailResponse(mobile, inUse);
        } else {
            throw new GlobalException(INVALID_PLAN);
        }
    }

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

