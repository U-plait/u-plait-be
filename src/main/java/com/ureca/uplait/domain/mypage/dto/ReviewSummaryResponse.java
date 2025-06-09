package com.ureca.uplait.domain.mypage.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewSummaryResponse {
    private Long reviewId;
    private Long planId;                    //해당하는 요금제 id..인데 이거 말고 상응하는 요금제 제목을 가져오는게 맞지않나 싶기도 하고
    private String title;                   //제목 -> 내용을 넣을까 말까? 이 리뷰 목록에서 리뷰를 누르면 요금제 상세페이지로 넘어가도록?
    private int rating;                     //별점
    private LocalDateTime createdAt;        //작성일
}
