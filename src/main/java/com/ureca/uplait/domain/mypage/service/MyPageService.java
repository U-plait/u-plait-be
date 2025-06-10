package com.ureca.uplait.domain.mypage.service;

import com.ureca.uplait.domain.mypage.dto.response.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.request.MyPageUpdateRequest;
import com.ureca.uplait.domain.mypage.dto.response.MyPageUpdateResponse;
import com.ureca.uplait.domain.mypage.dto.response.MyReviewsResponse;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public MyPageResponse getMyPage(User user) {
        return new MyPageResponse(
                user.getName()
                , user.getPhoneNumber()
                , user.getEmail()
                , user.getAge()
                , user.getGender()
                , user.getAdAgree()
        );
    }

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

    public List<MyReviewsResponse> getMyReview(Long userId) {
        List<Review> reviewList = reviewRepository.findByUserId(userId);

        return reviewList.stream()
                .map(review -> new MyReviewsResponse(
                        review.getId(),
                        review.getPlan().getId(),
                        review.getPlan().getPlanName(),
                        review.getTitle(),
                        review.getRating(),
                        review.getCreatedAt()
                ))
                .toList();
    }
}