package com.ureca.uplait.domain.admin.service;

import com.ureca.uplait.domain.admin.dto.response.AdminDetailReviewResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminReviewDeleteResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminReviewResponse;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.review.entity.Review;
import com.ureca.uplait.domain.review.repository.ReviewRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.enums.Gender;
import com.ureca.uplait.domain.user.enums.Role;
import com.ureca.uplait.domain.user.enums.Status;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private AdminReviewService adminReviewService;

    private User createUser() {
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

    User user;
    Review review1, review2;
    @BeforeEach
    void setUp() {
        MobilePlan plan1 = MobilePlan.builder().id(1L).planName("MobilePlan A").build();
        user = createUser();
        setId(user, 1L);

        review1 = Review.builder()
                .user(user)
                .plan(plan1)
                .title("title1")
                .content("content1")
                .rating(4)
                .build();
        setId(review1, 1L);

        review2 = Review.builder()
                .user(user)
                .plan(plan1)
                .title("title2")
                .content("content2")
                .rating(4)
                .build();
        setId(review2, 2L);
    }

    @DisplayName("관리자 리뷰 전체 조회")
    @Test
    void getAllReviewsForAdmin() {
        //given
        Pageable pageable = PageRequest.of(0, 20);
        AdminReviewResponse response1 = new AdminReviewResponse(
                review1.getId(),
                review1.getUser().getName(),
                review1.getTitle(),
                review1.getRating(),
                LocalDateTime.now()
        );
        AdminReviewResponse response2 = new AdminReviewResponse(
                review2.getId(),
                review2.getUser().getName(),
                review2.getTitle(),
                review2.getRating(),
                LocalDateTime.now()
        );

        Page<AdminReviewResponse> page = new PageImpl<>(List.of(response1, response2));

        when(reviewRepository.findAllReviewsForAdmin(pageable)).thenReturn(page);

        //when
        Page<AdminReviewResponse> resultPage = reviewRepository.findAllReviewsForAdmin(pageable);

        //then
        assertEquals(2, resultPage.getTotalElements());
        assertEquals(1, resultPage.getTotalPages());
        assertEquals("title1", resultPage.getContent().get(0).getTitle());
        assertEquals("title2", resultPage.getContent().get(1).getTitle());

        verify(reviewRepository).findAllReviewsForAdmin(pageable);
    }

    @DisplayName("관리자 리뷰 전체 조회 - 성공")
    @Test
    void getReviewDetailForAdmin() {
        //given
        when(reviewRepository.findById(user.getId())).thenReturn(Optional.of(review1));

        //when
        AdminDetailReviewResponse response = adminReviewService.getReviewDetailForAdmin(user.getId(), user);

        //then
        assertEquals(1L, response.getReviewId());
        assertEquals("title1", response.getTitle());
        assertEquals("content1", response.getContent());
        assertEquals("홍길동", response.getUserName());
        assertEquals(4, response.getRating());
        verify(reviewRepository).findById(user.getId());
    }

    @DisplayName("관리자 리뷰 전체 조회 - 예외")
    @Test
    void getReviewDetailForAdmin_Exception() {
        //given
        when(reviewRepository.findById(review1.getId())).thenReturn(Optional.empty());

        //when & then
        GlobalException exception = assertThrows(GlobalException.class, () -> {
            adminReviewService.getReviewDetailForAdmin(review1.getId(), user);
        });

        assertEquals(ResultCode.REVIEW_NOT_FOUND, exception.getResultCode());
    }

    @DisplayName("관리자 리뷰 삭제 (reviewId만 가지고 삭제) - 성공")
    @Test
    void deleteReviewById() {
        //given
        when(reviewRepository.existsById(review1.getId())).thenReturn(true);

        //when
        AdminReviewDeleteResponse response = adminReviewService.deleteReviewById(review1.getId());

        //then
        assertEquals(review1.getId(), response.getReviewId());
        verify(reviewRepository).deleteById(review1.getId());
    }

    @DisplayName("관리자 리뷰 삭제 (reviewId만 가지고 삭제) - 예외")
    @Test
    void deleteReviewById_Exception() {
        //given
        when(reviewRepository.existsById(review1.getId())).thenReturn(false);

        //when & then
        GlobalException exception = assertThrows(GlobalException.class, () -> {
            adminReviewService.deleteReviewById(review1.getId());
        });

        assertEquals(ResultCode.REVIEW_NOT_FOUND, exception.getResultCode());
    }
}