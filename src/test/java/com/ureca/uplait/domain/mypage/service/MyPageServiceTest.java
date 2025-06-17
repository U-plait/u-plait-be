package com.ureca.uplait.domain.mypage.service;

import com.ureca.uplait.domain.mypage.dto.request.MyPageUpdateRequest;
import com.ureca.uplait.domain.mypage.dto.response.MyPageResponse;
import com.ureca.uplait.domain.mypage.dto.response.MyPageUpdateResponse;
import com.ureca.uplait.domain.mypage.dto.response.MyReviewsResponse;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.entity.PlanType;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class MyPageServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReviewRepository reviewRepository;

    private MyPageService myPageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        myPageService = new MyPageService(userRepository, reviewRepository);
    }

    @DisplayName("내 정보 가져오기")
    @Test
    void getMyPage() {
        //given
        User user = User.builder()
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

        //when
        MyPageResponse myPageResponse = myPageService.getMyPage(user);

        //then
        assertEquals("홍길동", myPageResponse.getName());
        assertEquals("000-0000-0000", myPageResponse.getPhoneNumber());
        assertEquals("asdad@kakao.com", myPageResponse.getEmail());
        assertEquals(32, myPageResponse.getAge());
        assertEquals(Gender.UNKNOWN, myPageResponse.getGender());
        assertEquals(false, myPageResponse.isAdAgree());
    }

    @DisplayName("내 정보 수정하기")
    @Test
    void updateMyPage() {
        //given
        User user = User.builder()
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

        MyPageUpdateRequest request = new MyPageUpdateRequest(
                "010-2123-4567",
                "ghdrlfehd@kakao.com",
                true
        );

        //when
        MyPageUpdateResponse myPageUpdateResponse = myPageService.updateMyPage(user, request);

        //then
        assertEquals(user.getId(), myPageUpdateResponse.getUserId());
        assertEquals("010-2123-4567", user.getPhoneNumber());
        assertEquals("ghdrlfehd@kakao.com", user.getEmail());
        assertEquals(true, user.getAdAgree());
    }

    @DisplayName("내가 쓴 리뷰 가져오기-성공 케이스")
    @Test
    void getMyReview() {
        //given
        User user = User.builder()
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

        MobilePlan plan1 = MobilePlan.builder().id(1L).planName("MobilePlan A").build();
        InternetPlan plan2 = InternetPlan.builder().id(2L).planName("InternetPlan A").build();

        Review review1 = Review.builder()
                .user(user)
                .plan(plan1)
                .title("title1")
                .content("content1")
                .rating(4)
                .build();
        Review review2 = Review.builder()
                .user(user)
                .plan(plan2)
                .title("title2")
                .content("content2")
                .rating(5)
                .build();

        given(reviewRepository.findByUserIdWithPlan(user.getId()))
                .willReturn(List.of(review1, review2));

        //when
        List<MyReviewsResponse> responseList = myPageService.getMyReview(user.getId());

        //then
        assertEquals(2, responseList.size());

        MyReviewsResponse response1 = responseList.get(0);
        assertEquals(review1.getId(), response1.getReviewId());
        assertEquals(plan1.getId(), response1.getPlanId());
        assertEquals(plan1.getPlanName(), response1.getPlanName());
        assertEquals(PlanType.MOBILE, response1.getPlanType());
        assertEquals("title1", response1.getTitle());
        assertEquals("content1", response1.getContent());
        assertEquals(4, response1.getRating());

        MyReviewsResponse response2 = responseList.get(1);
        assertEquals(review2.getId(), response2.getReviewId());
        assertEquals(plan2.getId(), response2.getPlanId());
        assertEquals(plan2.getPlanName(), response2.getPlanName());
        assertEquals(PlanType.INTERNET, response2.getPlanType());
        assertEquals("title2", response2.getTitle());
        assertEquals("content2", response2.getContent());
        assertEquals(5, response2.getRating());
    }

    @DisplayName("내가 쓴 리뷰 가져오기-예외 발생 케이스")
    @Test
    void getMyReview_ThrowsException() {
        // given
        User user = User.builder()
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

        Plan unknownPlan = new Plan() {};

        Review review = Review.builder().plan(unknownPlan).build();

        when(reviewRepository.findByUserIdWithPlan(user.getId()))
                .thenReturn(List.of(review));

        // then
        GlobalException exception = assertThrows(GlobalException.class, () -> {
            myPageService.getMyReview(user.getId());
        });

        assertEquals(ResultCode.REVIEW_NOT_FOUND, exception.getResultCode());
    }
}