package com.ureca.uplait.domain.mypage.service;

import com.ureca.uplait.domain.mypage.dto.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.MyReviewsResponse;
import org.springframework.stereotype.Service;

public interface MyPageService {

    MyPageResponse getMyPage(Long userId);

    MyReviewsResponse getMyReview(Long id);
}
