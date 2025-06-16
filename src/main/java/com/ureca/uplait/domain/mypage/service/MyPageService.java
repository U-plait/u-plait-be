package com.ureca.uplait.domain.mypage.service;

import com.ureca.uplait.domain.mypage.dto.request.MyPageUpdateRequest;
import com.ureca.uplait.domain.mypage.dto.response.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.response.MyPageUpdateResponse;
import com.ureca.uplait.domain.mypage.dto.response.MyReviewsResponse;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.entity.PlanType;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        List<Review> reviewList = reviewRepository.findByUserIdWithPlan(userId);

        return reviewList.stream()
            .map(review -> {
                Plan plan = review.getPlan();
                PlanType planType;

                if (plan instanceof MobilePlan) {
                    planType = PlanType.MOBILE;
                } else if (plan instanceof InternetPlan) {
                    planType = PlanType.INTERNET;
                } else if (plan instanceof IPTVPlan) {
                    planType = PlanType.IPTV;
                } else {
                    throw new GlobalException(ResultCode.REVIEW_NOT_FOUND);
                }

                return new MyReviewsResponse(
                    review.getId(),
                    plan.getId(),
                    planType,
                    plan.getPlanName(),
                    review.getTitle(),
                    review.getContent(),
                    review.getRating(),
                    review.getCreatedAt()
                );
            })
            .toList();
    }
}