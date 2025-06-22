package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.global.exception.GlobalException;

import java.util.List;

import static com.ureca.uplait.global.response.ResultCode.INVALID_PLAN;

public class PlanResponseFactory {

    public static PlanDetailResponse from(Plan plan, List<Long> communityIdList, boolean inUse) {
        if (plan instanceof IPTVPlan iptv) {
            return new IPTVPlanDetailResponse(iptv, communityIdList, inUse);
        } else if (plan instanceof InternetPlan internet) {
            return new InternetPlanDetailResponse(internet, communityIdList, inUse);
        } else if (plan instanceof MobilePlan mobile) {
            return new MobilePlanDetailResponse(mobile, communityIdList, inUse);
        } else {
            throw new GlobalException(INVALID_PLAN);
        }
    }

    public static PlanDetailResponse from(boolean isBookmarked, Plan plan) {
        if (plan instanceof IPTVPlan iptv) {
            return new IPTVPlanDetailResponse(isBookmarked, iptv);
        } else if (plan instanceof InternetPlan internet) {
            return new InternetPlanDetailResponse(isBookmarked, internet);
        } else if (plan instanceof MobilePlan mobile) {
            return new MobilePlanDetailResponse(isBookmarked, mobile);
        } else {
            throw new GlobalException(INVALID_PLAN);
        }
    }
}

