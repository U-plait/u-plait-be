package com.ureca.uplait.domain.user.dto.response;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.global.exception.GlobalException;

import static com.ureca.uplait.global.response.ResultCode.INVALID_PLAN;

public class BookmarkResponseFactory {
    public static BookmarkResponse from(Plan plan, Long bookmarkId, boolean isBookmarked) {
        if (plan instanceof IPTVPlan iptv) {
            return new IPTVBookmarkResponse(iptv, bookmarkId, isBookmarked);
        } else if (plan instanceof InternetPlan internet) {
            return new InternetBookmarkResponse(internet, bookmarkId, isBookmarked);
        } else if (plan instanceof MobilePlan mobile) {
            return new MobileBookmarkResponse(mobile, bookmarkId, isBookmarked);
        } else {
            throw new GlobalException(INVALID_PLAN);
        }
    }
}
