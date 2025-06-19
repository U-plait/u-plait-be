package com.ureca.uplait.domain.admin.service;

import com.ureca.uplait.domain.admin.api.FastAPIClient;
import com.ureca.uplait.domain.admin.dto.request.AdminIPTVPlanCreateRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanCreateResponse;
import com.ureca.uplait.domain.community.entity.CommunityBenefit;
import com.ureca.uplait.domain.community.repository.CommunityBenefitPriceRepository;
import com.ureca.uplait.domain.community.repository.CommunityBenefitRepository;
import com.ureca.uplait.domain.community.repository.PlanCommunityRepository;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.user.entity.Tag;
import com.ureca.uplait.domain.user.repository.PlanTagRepository;
import com.ureca.uplait.domain.user.repository.TagRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminPlanServiceTest {

    @Mock
    private PlanRepository planRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private CommunityBenefitRepository communityBenefitRepository;

    @Mock
    private FastAPIClient fastAPIClient;

    @Mock
    PlanTagRepository planTagRepository;

    @Mock
    PlanCommunityRepository planCommunityRepository;

    @Mock
    CommunityBenefitPriceRepository communityBenefitPriceRepository;

    @InjectMocks
    private AdminPlanService adminPlanService;

    private void setSuperId(Object target, Long idValue) {
        try {
            Field idField = target.getClass().getSuperclass().getSuperclass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(target, idValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    @DisplayName("IPTV 요금제 생성 - 성공")
    @Test
    void createIptvPlan() {
        //given
        AdminIPTVPlanCreateRequest request = new AdminIPTVPlanCreateRequest();
        request.setPlanName("U+광랜 TV");
        request.setPlanPrice(33000);
        request.setPlanBenefit("기본 채널 180개 제공");
        request.setAvailability(true);
        request.setDescription("IPTV 기본 요금제");
        request.setTagIdList(List.of(1L, 2L));
        request.setCommunityBenefitList(List.of(10L, 20L));
        request.setChannel(180);
        request.setIptvDiscountRate(10);

        IPTVPlan iptvPlan = request.toIPTV(); // 실제로 저장될 객체
        setSuperId(iptvPlan, 99L);

        Tag tag1 = new Tag("무제한데이터");
        setId(tag1, 1L);
        Tag tag2 = new Tag("가성비요금제");
        setId(tag2, 2L);
        List<Tag> tagList = List.of(tag1, tag2);

        CommunityBenefit benefit1 = CommunityBenefit.builder()
                .name("가족 결합 복지")
                .maxPhone(3)
                .maxInternet(2)
                .maxIptv(1)
                .build();
        setId(benefit1, 10L);
        CommunityBenefit benefit2 = CommunityBenefit.builder()
                .name("노인 복지 결합")
                .maxPhone(3)
                .maxInternet(2)
                .maxIptv(1)
                .build();
        setId(benefit2, 20L);
        List<CommunityBenefit> communityBenefitList = List.of(benefit1, benefit2);

        when(planRepository.save(any(IPTVPlan.class))).thenReturn(iptvPlan);
        when(tagRepository.findAllById(request.getTagIdList())).thenReturn(tagList);
        when(communityBenefitRepository.findAllById(request.getCommunityBenefitList())).thenReturn(communityBenefitList);

        //when
        AdminPlanCreateResponse response = adminPlanService.createIptvPlan(request);

        //then
        assertNotNull(response);
        assertEquals(99L, response.getPlanId());

        verify(planRepository).save(any(IPTVPlan.class));
        verify(tagRepository).findAllById(eq(List.of(1L, 2L)));
        verify(communityBenefitRepository).findAllById(eq(List.of(10L, 20L)));
        verify(fastAPIClient).saveVector(any(IPTVPlan.class), anyString());
        verify(planTagRepository).saveAll(anyList());
        verify(planCommunityRepository).saveAll(anyList());
        verify(communityBenefitPriceRepository).findMaxHeadcountPricesByCommunityBenefitIds(anyList());
    }

    @DisplayName("IPTV 요금제 생성 - 예외")
    @Test
    void createIptvPlan_Exception() {
        //given
        AdminIPTVPlanCreateRequest request = new AdminIPTVPlanCreateRequest();
        request.setPlanName("중복 요금제 명");
        request.setPlanPrice(33000);
        request.setPlanBenefit("기본 채널 180개 제공");
        request.setAvailability(true);
        request.setDescription("IPTV 기본 요금제");
        request.setTagIdList(List.of(1L, 2L));
        request.setCommunityBenefitList(List.of(10L, 20L));
        request.setChannel(180);
        request.setIptvDiscountRate(10);

        IPTVPlan iptvPlan = request.toIPTV(); // 실제로 저장될 객체
        setSuperId(iptvPlan, 99L);

        when(planRepository.existsByPlanName("중복 요금제 명")).thenReturn(true);

        //when
        GlobalException exception = assertThrows(GlobalException.class, () -> {
            adminPlanService.createIptvPlan(request);
        });

        //then
        assertEquals(ResultCode.DUPLICATE_PLAN_NAME, exception.getResultCode());
        verify(planRepository).existsByPlanName("중복 요금제 명");
        verifyNoMoreInteractions(planRepository, tagRepository, communityBenefitRepository,
                planTagRepository, fastAPIClient);
    }

    @DisplayName("IPTV 요금제 수정")
    @Test
    void updateIptvPlan() {
        //given

        //when

        //then
    }

//    @DisplayName("IPTV 요금제 목록 조회")
//    @Test
//    void getAllIptvPlans() {
//        //given
//
//        //when
//
//        //then
//    }
//
//    @DisplayName("IPTV 요금제 삭제")
//    @Test
//    void getTypedPlanDetail() {
//        //given
//
//        //when
//
//        //then
//    }
}