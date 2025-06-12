package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.community.entity.CommunityBenefit;
import com.ureca.uplait.domain.community.entity.PlanCommunity;
import com.ureca.uplait.domain.community.repository.CommunityBenefitRepository;
import com.ureca.uplait.domain.community.repository.PlanCommunityRepository;
import com.ureca.uplait.domain.plan.dto.request.IPTVPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.response.CommunityBenefitResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanCreationInfoResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.TagResponse;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.user.entity.PlanTag;
import com.ureca.uplait.domain.user.entity.Tag;
import com.ureca.uplait.domain.user.repository.PlanTagRepository;
import com.ureca.uplait.domain.user.repository.TagRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminPlanService {

    private final PlanRepository planRepository;
    private final PlanTagRepository planTagRepository;
    private final TagRepository tagRepository;
    private final CommunityBenefitRepository communityBenefitRepository;
    private final PlanCommunityRepository planCommunityRepository;
    private final EntityManager em;

    /**
     * 요금제 생성
     */
    @Transactional
    public Long createMobilePlan(MobilePlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        // 요금제 정보 저장
        MobilePlan plan = planRepository.save(request.toMobile());

        // 태그 정보 저장
        savePlanTags(request.getTagIdList(), plan);

        // 결합 정보 저장
        saveCommunityBenefits(request.getCommunityBenefitList(), plan);

        return plan.getId();
    }

    @Transactional
    public Long createInternetPlan(InternetPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        // 요금제 정보 저장
        InternetPlan plan = planRepository.save(request.toInternet());

        // 태그 정보 저장
        savePlanTags(request.getTagIdList(), plan);

        // 결합 정보 저장
        saveCommunityBenefits(request.getCommunityBenefitList(), plan);

        return plan.getId();
    }

    @Transactional
    public Long createIptvPlan(IPTVPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        // 요금제 정보 저장
        IPTVPlan plan = planRepository.save(request.toIPTV());

        // 태그 정보 저장
        savePlanTags(request.getTagIdList(), plan);

        // 결합 정보 저장
        saveCommunityBenefits(request.getCommunityBenefitList(), plan);

        return plan.getId();
    }

    /**
     * 요금제 목록 조회
     */
    @Transactional(readOnly = true)
    public PageImpl<PlanDetailAdminResponse> getAllMobilePlans(Pageable pageable) {
        return planRepository.findAllMobilePlans(pageable);
    }

    @Transactional(readOnly = true)
    public PageImpl<PlanDetailAdminResponse> getAllInternetPlans(Pageable pageable) {
        return planRepository.findAllInternetPlans(pageable);
    }

    @Transactional(readOnly = true)
    public PageImpl<PlanDetailAdminResponse> getAllIPTVPlans(Pageable pageable) {
        return planRepository.findAllIPTVPlans(pageable);
    }

    /**
     * 요금제 상세 조회 (ADMIN)
     */
    @Transactional(readOnly = true)
    public PlanDetailAdminResponse getPlanDetail(Long planId) {
        Plan plan = getPlan(planId);
        return new PlanDetailAdminResponse(plan);
    }

    /**
     * 요금제 생성을 위한 정보 반환
     */
    public PlanCreationInfoResponse getPlanCreationInfo() {
        return new PlanCreationInfoResponse(
            tagRepository.findAll().stream().map(TagResponse::new).toList(),
            communityBenefitRepository.findAll().stream().map(CommunityBenefitResponse::new).toList()
        );
    }

    private void savePlanTags(List<Long> tagIdList, Plan plan) {
        List<PlanTag> planTagList = new ArrayList<>();
        for (Long tagId : tagIdList) {
            Tag tag = em.getReference(Tag.class, tagId);
            planTagList.add(new PlanTag(plan, tag));
        }
        planTagRepository.saveAll(planTagList);
    }

    private void saveCommunityBenefits(List<Long> communityBenefitIdList, Plan plan) {
        List<PlanCommunity> planCommunityList = new ArrayList<>();
        for (Long communityBenefitId : communityBenefitIdList) {
            CommunityBenefit communityBenefit = em.getReference(CommunityBenefit.class, communityBenefitId);
            planCommunityList.add(new PlanCommunity(plan, communityBenefit));
        }
        planCommunityRepository.saveAll(planCommunityList);
    }

    public Long deletePlanById(Long planId) {
        Plan plan = getPlan(planId);
        planRepository.delete(plan);
        return planId;
    }

    private Plan getPlan(Long id) {
        return planRepository.findById(id)
            .orElseThrow(() -> new GlobalException(ResultCode.PLAN_NOT_FOUND));
    }

    private void validateDuplicatePlanName(String name) {
        if (planRepository.existsByPlanName(name)) {
            throw new GlobalException(ResultCode.DUPLICATE_PLAN_NAME);
        }
    }
}
