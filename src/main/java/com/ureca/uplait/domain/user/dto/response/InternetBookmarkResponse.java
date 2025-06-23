package com.ureca.uplait.domain.user.dto.response;

import com.ureca.uplait.domain.plan.entity.InternetPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class InternetBookmarkResponse extends BookmarkResponse{
    @Schema(description = "할인 가격", example = "79500")
    private int internetDiscount;

    @Schema(description = "인터넷 속도", example = "1GB")
    private String velocity;

    public InternetBookmarkResponse(InternetPlan plan, Long bookmarkId, boolean isBookmarked) {
        super(plan, bookmarkId, isBookmarked);
        this.internetDiscount = plan.getInternetDiscountRate();
        this.velocity = plan.getVelocity();
    }
}
