package com.ureca.uplait.domain.user.dto.response;

import com.ureca.uplait.domain.plan.entity.Plan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BookmarkResponse {
    @Schema(description = "북마크 id", example = "true")
    private Long bookmarkId;

    @Schema(description = "요금제 id", example = "1")
    private Long planId;

    @Schema(description = "요금제 이름", example = "5G 프리미엄 요금제")
    private String planName;

    @Schema(description = "요금제 가격", example = "85000")
    private int planPrice;

    @Schema(description = "요금제 설명", example = "최고의 요금제")
    private String description;

    @Schema(description = "북마크 여부", example = "true")
    private boolean isBookmarked;

    public BookmarkResponse(Plan plan, Long bookmarkId, boolean isBookmarked) {
        this.bookmarkId = bookmarkId;
        this.planId = plan.getId();
        this.planName = plan.getPlanName();
        this.planPrice = plan.getPlanPrice();
        this.description = plan.getDescription();
        this.isBookmarked = isBookmarked;
    }
}
