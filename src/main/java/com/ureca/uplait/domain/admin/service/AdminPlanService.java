package com.ureca.uplait.domain.admin.service;

import com.ureca.uplait.domain.admin.dto.request.AdminIPTVPlanCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminIPTVPlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminInternetPlanCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminInternetPlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminMobileCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminMobilePlanUpdateRequest;
import com.ureca.uplait.domain.community.entity.CommunityBenefit;
import com.ureca.uplait.domain.community.entity.PlanCommunity;
import com.ureca.uplait.domain.community.repository.CommunityBenefitRepository;
import com.ureca.uplait.domain.community.repository.PlanCommunityRepository;
import com.ureca.uplait.domain.plan.dto.response.CommunityBenefitResponse;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanCreationInfoResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailAdminResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanResponseFactory;
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
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Long createMobilePlan(AdminMobileCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        MobilePlan plan = request.toMobile();

        planRepository.save(plan);

        savePlanTags(request.getTagIdList(), plan);
        saveCommunityBenefits(request.getCommunityBenefitList(), plan);

        return plan.getId();
    }

    public Long createInternetPlan(AdminInternetPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());
        InternetPlan plan = request.toInternet();
        planRepository.save(plan);

        savePlanTags(request.getTagIdList(), plan);
        saveCommunityBenefits(request.getCommunityBenefitList(), plan);
        return planRepository.save(plan).getId();
    }

    public Long createIptvPlan(AdminIPTVPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        IPTVPlan plan = request.toIPTV();
        planRepository.save(plan);
        savePlanTags(request.getTagIdList(), plan);
        saveCommunityBenefits(request.getCommunityBenefitList(), plan);
        return plan.getId();
    }

    public void updateMobilePlan(Long id, AdminMobilePlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof MobilePlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }
        ((MobilePlan) plan).mobileUpdateFrom(request);
    }

    public void updateIPTVPlan(Long id, AdminIPTVPlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof IPTVPlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }
        ((IPTVPlan) plan).IPTVUpdateForm(request);

    }

    public void updateInternetPlan(Long id, AdminInternetPlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof InternetPlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }
        ((InternetPlan) plan).InternetUpdateForm(request);
    }


    @Transactional(readOnly = true)
    public Page<MobilePlanDetailResponse> getAllMobilePlans(Pageable pageable) {
        return planRepository.findAllMobilePlans(pageable);
    }

    @Transactional(readOnly = true)
    public Page<InternetPlanDetailResponse> getAllInternetPlans(Pageable pageable) {
        return planRepository.findAllInternetPlans(pageable);
    }

    @Transactional(readOnly = true)
    public Page<IPTVPlanDetailResponse> getAllIPTVPlans(Pageable pageable) {
        return planRepository.findAllIPTVPlans(pageable);
    }

    @Transactional(readOnly = true)
    public PlanDetailAdminResponse getPlanDetail(Long planId) {
        Plan plan = getPlan(planId);
        return new PlanDetailAdminResponse(plan);
    }

    public PlanDetailResponse getTypedPlanDetail(String type, Long planId) {
        Plan plan = getPlan(planId);
        return PlanResponseFactory.from(plan);
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

    public PlanCreationInfoResponse getPlanCreationInfo() {
        return new PlanCreationInfoResponse(
            tagRepository.findAll().stream().map(TagResponse::new).toList(),
            communityBenefitRepository.findAll().stream().map(CommunityBenefitResponse::new)
                .toList()
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
            CommunityBenefit communityBenefit = em.getReference(CommunityBenefit.class,
                communityBenefitId);
            planCommunityList.add(new PlanCommunity(plan, communityBenefit));
        }
        planCommunityRepository.saveAll(planCommunityList);
    }

}
