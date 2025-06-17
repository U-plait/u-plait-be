package com.ureca.uplait.domain.review.service;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.review.dto.response.ReviewListResponse;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private ReviewService reviewService;

    User createUser() {
        return User.builder()
                .kakaoId("456465")
                .name("홍길동")
                .phoneNumber("000-0000-0000")
                .email("asdad@kakao.com")
                .age(32)
                .gender(Gender.UNKNOWN)
                .role(Role.TMP_USER)
                .status(Status.ACTIVE)
                .adAgree(false)
                .build();
    }

    @DisplayName("요금제별 리뷰 목록 조회")
    @Test
    void getReviewList() {
        //given
        User user = createUser();

        MobilePlan plan1 = MobilePlan.builder().id(1L).planName("MobilePlan A").build();

        Review review1 = Review.builder()
                .user(user)
                .plan(plan1)
                .title("title1")
                .content("content1")
                .rating(4)
                .build();
        Review review2 = Review.builder()
                .user(user)
                .plan(plan1)
                .title("title2")
                .content("content2")
                .rating(5)
                .build();

        //when
        ReviewListResponse reviewListResponse = reviewService.getReviewList(user, 5, 0L, plan1.getId());

        //then
    }

    @DisplayName("요금제별 리뷰 작성")
    @Test
    void createReview() {
        //given
        User user = createUser();

        //when

        //then
    }

    @DisplayName("요금제별 리뷰 수정")
    @Test
    void updateReview() {
        //given
        User user = createUser();

        //when

        //then
    }

    @DisplayName("요금제별 리뷰 삭제")
    @Test
    void deleteReview() {
        //given
        User user = createUser();

        //when

        //then
    }
}