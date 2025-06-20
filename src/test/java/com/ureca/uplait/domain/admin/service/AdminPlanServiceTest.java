package com.ureca.uplait.domain.admin.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.ureca.uplait.domain.admin.api.FastAPIClient;
import com.ureca.uplait.domain.admin.dto.request.AdminMobileCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminMobilePlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanCreateResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanDeleteResponse;
import com.ureca.uplait.domain.community.entity.CommunityBenefit;
import com.ureca.uplait.domain.community.entity.CommunityBenefitPrice;
import com.ureca.uplait.domain.community.repository.CommunityBenefitPriceRepository;
import com.ureca.uplait.domain.community.repository.CommunityBenefitRepository;
import com.ureca.uplait.domain.community.repository.PlanCommunityRepository;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MediaBenefit;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.user.entity.Tag;
import com.ureca.uplait.domain.user.repository.PlanTagRepository;
import com.ureca.uplait.domain.user.repository.TagRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;


@ExtendWith(MockitoExtension.class)
class AdminPlanServiceTest {

    @Mock
    private PlanRepository planRepository;
    @Mock
    private PlanTagRepository planTagRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private CommunityBenefitRepository communityBenefitRepository;
    @Mock
    private PlanCommunityRepository planCommunityRepository;
    @Mock
    private CommunityBenefitPriceRepository communityBenefitPriceRepository;
    @Mock
    private FastAPIClient fastAPIClient;

    @InjectMocks
    private AdminPlanService adminPlanService;


    @Test
    @DisplayName("모바일 플랜 생성 성공")
    void createMobilePlan_success() {
        // give
        AdminMobileCreateRequest request = new AdminMobileCreateRequest();
        request.setPlanName("mobilePlan");
        request.setTagIdList(List.of(1L, 2L));

        MobilePlan savePlan = MobilePlan.builder()
            .planName("새로운 모바일 플랜")
            .planPrice(4000)
            .planBenefit("혜택이 있습니다.")
            .data("100gb")
            .sharedData("무제한")
            .voiceCall("무제한")
            .message("무제한")
            .extraData("무제한")
            .mediaBenefit(MediaBenefit.PREMIUM)
            .durationDiscountRate(10)
            .premierDiscountRate(10)
            .build();

        ReflectionTestUtils.setField(savePlan, "id", 1L);

        List<Tag> tags = List.of(
            new Tag("태그 1"),
            new Tag("태그 2")
        );

        List<CommunityBenefit> benefits = List.of(
            new CommunityBenefit("혜택 1", 5, 5, 5, "혜택1 입니다.", "가족 결합"),
            new CommunityBenefit("혜택 2", 5, 5, 5, "혜택2 입니다.", "투게더 결합")
        );

        List<CommunityBenefitPrice> benefitPrices = List.of(
            new CommunityBenefitPrice(benefits.get(0), 1, 1, 12, 1000),
            new CommunityBenefitPrice(benefits.get(1), 1, 2, 12, 500)
        );

        given(planRepository.existsByPlanName(request.getPlanName())).willReturn(false);

        given(tagRepository.findAllById(request.getTagIdList())).willReturn(tags);

        given(
            communityBenefitPriceRepository.findMaxHeadcountPricesByCommunityBenefitIds(anyList()))
            .willReturn(benefitPrices);

        given(planRepository.save(any(MobilePlan.class))).willReturn(savePlan);

        // when
        AdminPlanCreateResponse response = adminPlanService.createMobilePlan(request);

        // then
        assertThat(response.getPlanId()).isEqualTo(savePlan.getId());

        verify(planRepository, times(1)).existsByPlanName(request.getPlanName());
        verify(planRepository, times(1)).save(any(MobilePlan.class));
        verify(tagRepository, times(1)).findAllById(request.getTagIdList());
        verify(planTagRepository, times(1)).saveAll(anyList());
        verify(planCommunityRepository, times(1)).saveAll(anyList());
        verify(communityBenefitPriceRepository,
            times(1)).findMaxHeadcountPricesByCommunityBenefitIds(anyList());
    }

    @Test
    @DisplayName("모바일 플랜 생성 실패 - 플랜 이름 중복")
    void createMobilePlan_duplicatePlanName_throwsException() {
        // Given
        AdminMobileCreateRequest request = new AdminMobileCreateRequest();
        request.setPlanName("중복된 플랜 이름");

        given(planRepository.existsByPlanName(request.getPlanName())).willReturn(true);

        GlobalException exception = assertThrows(GlobalException.class, () ->
            adminPlanService.createMobilePlan(request));

        assertThat(exception.getResultCode()).isEqualTo(ResultCode.DUPLICATE_PLAN_NAME);

        verify(planRepository, times(1)).existsByPlanName(request.getPlanName());
        verify(planRepository, never()).save(any(MobilePlan.class));
        verify(tagRepository, never()).findAllById(anyList());
        verify(communityBenefitRepository, never()).findAllById(anyList());
        verify(planTagRepository, never()).saveAll(anyList());
        verify(planCommunityRepository, never()).saveAll(anyList());
        verify(fastAPIClient, never()).saveVector(any(Plan.class), anyString());
    }

    @Test
    @DisplayName("모바일 플랜 업데이트 성공")
    void updateMobilePlan_success() {
        // give
        Long planId = 1L;
        AdminMobilePlanUpdateRequest request = new AdminMobilePlanUpdateRequest();
        request.setPlanName("업데이트된 모바일 플랜");

        MobilePlan existingPlan = MobilePlan.builder()
            .id(planId)
            .planName("기존 모바일 플랜")
            .build();

        given(planRepository.findById(planId)).willReturn(Optional.of(existingPlan));

        // when
        adminPlanService.updateMobilePlan(planId, request);

        // then
        verify(planRepository, times(1)).findById(planId);
        assertThat(existingPlan.getPlanName()).isEqualTo(request.getPlanName());
    }

    @Test
    @DisplayName("플랜 ID로 찾을 수 없어 업데이트 실패")
    void updateMobilePlan_notFound_throwsException() {
        // given
        Long planId = 99L;
        AdminMobilePlanUpdateRequest request = new AdminMobilePlanUpdateRequest();

        given(planRepository.findById(planId)).willReturn(Optional.empty());

        // when
        GlobalException exception = assertThrows(GlobalException.class, () ->
            adminPlanService.updateMobilePlan(planId, request));
        // then
        assertThat(exception.getResultCode()).isEqualTo(ResultCode.PLAN_NOT_FOUND);
        verify(planRepository, times(1)).findById(planId);
    }

    @Test
    @DisplayName(" ID에 해당하는 플랜이 MobilePlan이 아닐 경우 실패")
    void updateMobilePlan_invalidPlanType_throwsException() {
        // given
        Long planId = 1L;
        AdminMobilePlanUpdateRequest request = new AdminMobilePlanUpdateRequest();
        InternetPlan internetPlan = InternetPlan.builder().id(planId).planName("인터넷 플랜").build();

        given(planRepository.findById(planId)).willReturn(Optional.of(internetPlan));

        // when
        GlobalException exception = assertThrows(GlobalException.class, () ->
            adminPlanService.updateMobilePlan(planId, request));
        // then
        assertThat(exception.getResultCode()).isEqualTo(ResultCode.INVALID_PLAN);
        verify(planRepository, times(1)).findById(planId);
    }

    @Test
    @DisplayName("모든 모바일 플랜 조회 성공")
    void getAllMobilePlans_success() {
        // Given
        Pageable pageable = Pageable.ofSize(10);
        MobilePlan plan1 = MobilePlan.builder()
            .id(1L)
            .planName("폰플랜1")
            .planPrice(30000)
            .planBenefit("혜택1")
            .availability(true)
            .data("5G")
            .sharedData("데이터 무제한")
            .voiceCall("무제한")
            .message("무제한")
            .extraData("무제한")
            .mediaBenefit(MediaBenefit.PREMIUM)
            .durationDiscountRate(10)
            .premierDiscountRate(5)
            .planTags(new HashSet<>())
            .communityBenefitList(new HashSet<>())
            .build();

        MobilePlan plan2 = MobilePlan.builder()
            .id(2L)
            .planName("폰플랜2")
            .planPrice(20000)
            .planBenefit("혜택2")
            .availability(true)
            .data("LTE")
            .sharedData("데이터 50GB")
            .voiceCall("무제한")
            .message("무제한")
            .extraData("무제한")
            .mediaBenefit(MediaBenefit.PREMIUM)
            .durationDiscountRate(5)
            .premierDiscountRate(2)
            .planTags(new HashSet<>())
            .communityBenefitList(new HashSet<>())
            .build();

        List<MobilePlanDetailResponse> mockResponses = List.of(
            new MobilePlanDetailResponse(false, plan1),
            new MobilePlanDetailResponse(false, plan2)
        );
        Page<MobilePlanDetailResponse> mockPage = new PageImpl<>(mockResponses, pageable, 2);

        given(planRepository.findAllMobilePlans(pageable, null)).willReturn(mockPage);

        // When
        Page<MobilePlanDetailResponse> result = adminPlanService.getAllMobilePlans(pageable, null);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getPlanName()).isEqualTo("폰플랜1");
        assertThat(result.getContent().get(1).getPlanName()).isEqualTo("폰플랜2");
        verify(planRepository, times(1)).findAllMobilePlans(pageable, null);
    }

    @Test
    @DisplayName("플랜 삭제 성공")
    void deletePlanById_success() {
        // given
        Long planId = 1L;
        Plan planToDelete = MobilePlan.builder().id(planId).planName("삭제될 플랜").build();

        given(planRepository.findById(planId)).willReturn(Optional.of(planToDelete));

        // when
        AdminPlanDeleteResponse response = adminPlanService.deletePlanById(planId);

        // then
        assertThat(response.getPlanId()).isEqualTo(planId);
        verify(planRepository, times(1)).findById(planId);
        verify(planRepository, times(1)).delete(planToDelete);
    }

    @Test
    @DisplayName("플랜 ID로 찾을 수 없어 삭제 실패")
    void deletePlanById_notFound_throwsException() {
        // given
        Long planId = 99L;
        given(planRepository.findById(planId)).willReturn(Optional.empty());

        // when
        GlobalException exception = assertThrows(GlobalException.class, () ->
            adminPlanService.deletePlanById(planId));
        // then
        assertThat(exception.getResultCode()).isEqualTo(ResultCode.PLAN_NOT_FOUND);
        verify(planRepository, times(1)).findById(planId);
        verify(planRepository, never()).delete(any(Plan.class));
    }
}