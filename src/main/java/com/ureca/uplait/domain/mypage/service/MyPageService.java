package com.ureca.uplait.domain.mypage.service;

import com.ureca.uplait.domain.mypage.dto.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.MyPageUpdateRequest;
import com.ureca.uplait.domain.mypage.dto.MyPageUpdateResponse;
import com.ureca.uplait.domain.mypage.dto.MyReviewsResponse;
import com.ureca.uplait.domain.user.entity.User;

import java.util.List;

public interface MyPageService {

    MyPageResponse getMyPage(Long userId);

    List<MyReviewsResponse> getMyReview(Long id);

    MyPageUpdateResponse updateMyPage(User user, MyPageUpdateRequest myPageUpdateRequest);
}
