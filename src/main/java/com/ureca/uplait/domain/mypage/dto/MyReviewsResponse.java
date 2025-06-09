package com.ureca.uplait.domain.mypage.dto;

import com.ureca.uplait.domain.review.entity.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Schema(description = "내가 쓴 리뷰 응답")
@AllArgsConstructor
public class MyReviewsResponse {

    private Long reviewId;
    private Long planId;                    //해당하는 요금제 id..인데 이거 말고
    private String planName;                //상응하는 요금제 제목을 가져오는게 맞지않나 싶기도 하고
    private String title;                   //제목 -> 내용을 넣을까 말까? 이 리뷰 목록에서 리뷰를 누르면 요금제 상세페이지로 넘어가도록?
    private int rating;                     //별점
    private LocalDateTime createdAt;        //작성일

    public static MyReviewsResponse from(Review review) {
        return new MyReviewsResponse(
                review.getId()
                , review.getPlan().getId()
                , review.getPlan().getPlanName()
                , review.getTitle()
                , review.getRating()
                , review.getCreatedAt()
        );
    }
}
