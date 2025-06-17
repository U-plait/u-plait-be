package com.ureca.uplait.domain.review.service;

import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.review.dto.request.ReviewCreateRequest;
import com.ureca.uplait.domain.review.dto.request.ReviewUpdateRequest;
import com.ureca.uplait.domain.review.dto.response.*;
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

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    private void setId(Object target, Long idValue) {
        try {
            Field idField = target.getClass().getSuperclass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(target, idValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCreatedAt(Object target, LocalDateTime createdAt) {
        try {
            Field idField = target.getClass().getSuperclass().getDeclaredField("createdAt");
            idField.setAccessible(true);
            idField.set(target, createdAt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("요금제별 리뷰 목록 조회 - 리뷰 1개씩 페이지네이션하는 경우")
    @Test
    void getReviewList() {
        //given
        MobilePlan plan1 = MobilePlan.builder().id(1L).planName("MobilePlan A").build();

        User user1 = createUser();
        setId(user1, 1L);

        Review review1 = Review.builder()
                .user(user1)
                .plan(plan1)
                .title("title1")
                .content("content1")
                .rating(4)
                .build();
        setId(review1, 1L);
        setCreatedAt(review1, LocalDateTime.now());

        User user2 = createUser();
        setId(user2, 2L);

        Review review2 = Review.builder()
                .user(user2)
                .plan(plan1)
                .title("title2")
                .content("content2")
                .rating(5)
                .build();
        setId(review2, 2L);
        setCreatedAt(review2, LocalDateTime.now());

        when(reviewRepository.getReviewsByPlanAndPage(2, null, plan1.getId()))
                .thenReturn(List.of(review1, review2));

        //when
        ReviewListResponse reviewListResponse
                = reviewService.getReviewList(user1, 1, null, plan1.getId());

        //then
        assertEquals(true, reviewListResponse.isHasNext());
        assertEquals(1, reviewListResponse.getReviewList().size());

        ReviewResponse reviewResponse = reviewListResponse.getReviewList().get(0);
        assertEquals(review1.getUser().getName(), reviewResponse.getUserName());
        assertEquals(review1.getTitle(), reviewResponse.getTitle());
        assertEquals(review1.getContent(), reviewResponse.getContent());
        assertEquals(review1.getRating(), reviewResponse.getRating());
        assertNotNull(reviewResponse.getCreatedAt());

        assertNotEquals(review2.getTitle(), reviewResponse.getTitle());
    }

    @DisplayName("요금제별 리뷰 작성")
    @Test
    void createReview() {
        //given
        MobilePlan plan1 = MobilePlan.builder().id(1L).planName("MobilePlan A").build();

        User user = createUser();
        setId(user, 1L);

        ReviewCreateRequest request = new ReviewCreateRequest(
                plan1.getId(),
                "리뷰 제목",
                "리뷰 내용",
                4
        );

        Review review = new Review(user, plan1, request.getTitle(), request.getContent(), request.getRating());
        Review savedReview = new Review(user, plan1, request.getTitle(), request.getContent(), request.getRating());
        setId(savedReview, 10L);

        when(planRepository.findById(plan1.getId())).thenReturn(Optional.of(plan1));
        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);

        //when
        ReviewCreateResponse response = reviewService.createReview(user, request);

        //then
        assertEquals(savedReview.getId(), response.getReviewId());
    }

    @DisplayName("요금제별 리뷰 수정")
    @Test
    void updateReview() {
        //given
        MobilePlan plan1 = MobilePlan.builder().id(1L).planName("MobilePlan A").build();

        User user = createUser();
        setId(user, 1L);
        
        Review review = new Review(user, plan1, "리뷰 제목", "리뷰 내용", 4);
        setId(review, 10L);

        ReviewUpdateRequest request = new ReviewUpdateRequest(
                review.getId(),
                "수정한 리뷰 제목",
                "수정한 리뷰 내용",
                5
        );

        when(reviewRepository.findById(request.getReviewId())).thenReturn(Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        //when
        ReviewUpdateResponse response = reviewService.updateReview(user, request);

        //then
        assertEquals(review.getId(), response.getReviewId());
        assertEquals("수정한 리뷰 제목", review.getTitle());
        assertEquals("수정한 리뷰 내용", review.getContent());
        assertEquals(5, review.getRating());
    }

    @DisplayName("요금제별 리뷰 삭제")
    @Test
    void deleteReview() {
        //given
        MobilePlan plan1 = MobilePlan.builder().id(1L).planName("MobilePlan A").build();

        User user = createUser();
        setId(user, 1L);

        Review review = new Review(user, plan1, "리뷰 제목", "리뷰 내용", 4);
        setId(review, 10L);

        //when
        ReviewDeleteResponse response = reviewService.deleteReview(user, review.getId());

        //then
        verify(reviewRepository).deleteById(review.getId());
        assertEquals(review.getId(), response.getReviewId());
    }
}