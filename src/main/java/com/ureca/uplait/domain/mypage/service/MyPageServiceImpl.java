package com.ureca.uplait.domain.mypage.service;

import com.ureca.uplait.domain.mypage.dto.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.MyReviewsResponse;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public MyPageResponse getMyPage(Long userId) {
                                                                            //권한이 없는 사용자가 접근시 포비든 처리
        User user = userRepository.findById(userId).orElseThrow(()-> new GlobalException(ResultCode.FORBIDDEN));
        return MyPageResponse.from(user);
    }

    @Override
    public MyReviewsResponse getMyReview(Long userId) {
        Review review = reviewRepository.findByUserId(userId).orElse(null);
        
        return null;
    }
}
