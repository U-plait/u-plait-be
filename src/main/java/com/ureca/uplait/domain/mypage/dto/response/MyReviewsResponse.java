package com.ureca.uplait.domain.mypage.dto.response;

import com.ureca.uplait.domain.review.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "내가 쓴 리뷰 응답")
@AllArgsConstructor
public class MyReviewsResponse {

    @Schema(description = "리뷰 id", example = "1")
    private Long reviewId;
    @Schema(description = "요금제 id", example = "1")
    private Long planId;                    //해당하는 요금제 id..인데 이거 말고
    @Schema(description = "요금제 이름", example = "5G 요금제")
    private String planName;                //상응하는 요금제 제목을 가져오는게 맞지않나 싶기도 하고
    @Schema(description = "리뷰 제목", example = "이 요금제 끝내줘요")
    private String title;                   //제목 -> 내용을 넣을까 말까? 이 리뷰 목록에서 리뷰를 누르면 리뷰 상세페이지로 넘어가도록?
    @Schema(description = "별점", example = "5")
    private int rating;                     //별점
    @Schema(description = "작성일", example = "25.06.08")
    private LocalDateTime createdAt;        //작성일
}
