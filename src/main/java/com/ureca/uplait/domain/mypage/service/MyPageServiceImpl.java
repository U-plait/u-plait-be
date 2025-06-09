package com.ureca.uplait.domain.mypage.service;

import com.ureca.uplait.domain.mypage.dto.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.MyPageUpdateRequest;
import com.ureca.uplait.domain.mypage.dto.MyPageUpdateResponse;
import com.ureca.uplait.domain.mypage.dto.MyReviewsResponse;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional
    public MyPageUpdateResponse updateMyPage(User user, MyPageUpdateRequest myPageUpdateRequest) {
        user.updateUser(
                myPageUpdateRequest.getPhoneNumber()
                , myPageUpdateRequest.getEmail()
                , myPageUpdateRequest.isAdAgree()
        );

        userRepository.save(user);
        return new MyPageUpdateResponse(user.getId());
    }

    @Override
    public List<MyReviewsResponse> getMyReview(Long userId) {
        List<Review> reviewList = reviewRepository.findByUserId(userId).orElse(null); //null이면 프론트엔드 측에서 처리할 수 있도록
        List<MyReviewsResponse> list;
        if(reviewList != null) {
            list = new ArrayList<>(reviewList.size());
            for (Review review : reviewList) {
                MyReviewsResponse myReviewsResponse = MyReviewsResponse.from(review);
                list.add(myReviewsResponse);
            }
        } else{                                                                    //리뷰가 하나도 없으면 프론트엔드 측에서 처리할 수 있도록
            list = null;
        }

        return list;
    }
}
