package com.ureca.uplait.domain.user.dto.response;

import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class IPTVBookmarkResponse extends BookmarkResponse{
    @Schema(description = "할인 가격", example = "79500")
    private int discountPrice;

    @Schema(description = "채널 수", example = "255")
    private int channel;

    public IPTVBookmarkResponse(IPTVPlan plan, Long bookmarkId, boolean isBookmarked) {
        super(plan, bookmarkId, isBookmarked);
        this.discountPrice = plan.getIptvDiscountRate();
        this.channel = plan.getChannel();
    }
}
