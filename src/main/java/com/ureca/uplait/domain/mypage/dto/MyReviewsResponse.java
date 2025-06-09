package com.ureca.uplait.domain.mypage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "내가 쓴 리뷰 응답")
public class MyReviewsResponse {

    private List<ReviewSummaryResponse> reviews;        //내가 쓴 리뷰

}
