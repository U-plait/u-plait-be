package com.ureca.uplait.domain.admin.service;

import static com.ureca.uplait.domain.plan.util.DescriptionUtil.createDescription;

import com.ureca.uplait.domain.admin.api.FastAPIClient;
import com.ureca.uplait.domain.admin.dto.request.AdminIPTVPlanCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminIPTVPlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminInternetPlanCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminInternetPlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminMobileCreateRequest;
import com.ureca.uplait.domain.admin.dto.request.AdminMobilePlanUpdateRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanCreateResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanDeleteResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminPlanDetailResponse;
import com.ureca.uplait.domain.admin.dto.response.AdminUpdateAllVectorResponse;
import com.ureca.uplait.domain.admin.repository.PlanVectorJdbcRepository;
import com.ureca.uplait.domain.community.entity.CommunityBenefit;
import com.ureca.uplait.domain.community.entity.CommunityBenefitPrice;
import com.ureca.uplait.domain.community.entity.PlanCommunity;
import com.ureca.uplait.domain.community.repository.CommunityBenefitPriceRepository;
import com.ureca.uplait.domain.community.repository.CommunityBenefitRepository;
import com.ureca.uplait.domain.community.repository.PlanCommunityRepository;
import com.ureca.uplait.domain.plan.dto.response.CommunityBenefitResponse;
import com.ureca.uplait.domain.plan.dto.response.IPTVPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.InternetPlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.MobilePlanDetailResponse;
import com.ureca.uplait.domain.plan.dto.response.PlanCreationInfoResponse;
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
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.PlanTagRepository;
import com.ureca.uplait.domain.user.repository.TagRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
    private final CommunityBenefitPriceRepository communityBenefitPriceRepository;
    private final PlanVectorJdbcRepository planVectorJdbcRepository;
    private final FastAPIClient fastAPIClient;

    @Transactional
    public AdminPlanCreateResponse createMobilePlan(AdminMobileCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        MobilePlan plan = request.toMobile();
        MobilePlan savedPlan = planRepository.save(plan);

        List<Tag> tagList = tagRepository.findAllById(request.getTagIdList());
        List<CommunityBenefit> communityBenefitList = communityBenefitRepository.findAllById(
            request.getCommunityBenefitIdList());

        savePlanTags(tagList, savedPlan);
        saveCommunityBenefits(communityBenefitList, savedPlan);

        String description = createDescription(savedPlan, tagList,
            getPricesGroupedByBenefit(communityBenefitList));
        fastAPIClient.saveVector(savedPlan, description);

        return new AdminPlanCreateResponse(savedPlan.getId());
    }

    @Transactional
    public AdminPlanCreateResponse createInternetPlan(AdminInternetPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        InternetPlan plan = request.toInternet();
        InternetPlan savedPlan = planRepository.save(plan);

        List<Tag> tagList = tagRepository.findAllById(request.getTagIdList());
        List<CommunityBenefit> communityBenefitList = communityBenefitRepository.findAllById(
            request.getCommunityBenefitIdList());

        savePlanTags(tagList, savedPlan);
        saveCommunityBenefits(communityBenefitList, savedPlan);

        String description = createDescription(savedPlan, tagList,
            getPricesGroupedByBenefit(communityBenefitList));
        fastAPIClient.saveVector(savedPlan, description);

        return new AdminPlanCreateResponse(savedPlan.getId());
    }

    @Transactional
    public AdminPlanCreateResponse createIptvPlan(AdminIPTVPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        IPTVPlan plan = request.toIPTV();
        IPTVPlan savedPlan = planRepository.save(plan);

        List<Tag> tagList = tagRepository.findAllById(request.getTagIdList());
        List<CommunityBenefit> communityBenefitList = communityBenefitRepository.findAllById(
            request.getCommunityBenefitIdList());

        savePlanTags(tagList, savedPlan);
        saveCommunityBenefits(communityBenefitList, savedPlan);

        String description = createDescription(savedPlan, tagList,
            getPricesGroupedByBenefit(communityBenefitList));
        fastAPIClient.saveVector(savedPlan, description);

        return new AdminPlanCreateResponse(savedPlan.getId());
    }

    @Transactional
    public void updateMobilePlan(Long id, AdminMobilePlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof MobilePlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }

        MobilePlan mobilePlan = (MobilePlan) plan;
        mobilePlan.mobileUpdateFrom(request);

        planTagRepository.deleteAllByPlan(mobilePlan);
        planCommunityRepository.deleteAllByPlan(mobilePlan);

        List<Tag> tagList = tagRepository.findAllById(request.getTagIdList());
        List<CommunityBenefit> communityBenefitList = communityBenefitRepository.findAllById(
            request.getCommunityBenefitIdList());

        savePlanTags(tagList, mobilePlan);
        saveCommunityBenefits(communityBenefitList, mobilePlan);

        String description = createDescription(mobilePlan, tagList,
            getPricesGroupedByBenefit(communityBenefitList));
        fastAPIClient.saveVector(mobilePlan, description);
    }

    @Transactional
    public void updateIPTVPlan(Long id, AdminIPTVPlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof IPTVPlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }

        IPTVPlan iptvPlan = (IPTVPlan) plan;
        iptvPlan.IPTVUpdateForm(request);

        planTagRepository.deleteAllByPlan(iptvPlan);
        planCommunityRepository.deleteAllByPlan(iptvPlan);

        List<Tag> tagList = tagRepository.findAllById(request.getTagIdList());
        List<CommunityBenefit> communityBenefitList = communityBenefitRepository.findAllById(
            request.getCommunityBenefitIdList());

        savePlanTags(tagList, iptvPlan);
        saveCommunityBenefits(communityBenefitList, iptvPlan);

        String description = createDescription(iptvPlan, tagList,
            getPricesGroupedByBenefit(communityBenefitList));
        fastAPIClient.saveVector(iptvPlan, description);
    }

    @Transactional
    public void updateInternetPlan(Long id, AdminInternetPlanUpdateRequest request) {
        Plan plan = getPlan(id);
        if (!(plan instanceof InternetPlan)) {
            throw new GlobalException(ResultCode.INVALID_PLAN);
        }

        InternetPlan internetPlan = (InternetPlan) plan;
        internetPlan.InternetUpdateForm(request);

        planTagRepository.deleteAllByPlan(internetPlan);
        planCommunityRepository.deleteAllByPlan(internetPlan);

        List<Tag> tagList = tagRepository.findAllById(request.getTagIdList());
        List<CommunityBenefit> communityBenefitList = communityBenefitRepository.findAllById(
            request.getCommunityBenefitIdList());

        savePlanTags(tagList, internetPlan);
        saveCommunityBenefits(communityBenefitList, internetPlan);

        String description = createDescription(internetPlan, tagList,
            getPricesGroupedByBenefit(communityBenefitList));
        fastAPIClient.saveVector(internetPlan, description);
    }

    @Transactional(readOnly = true)
    public Page<MobilePlanDetailResponse> getAllMobilePlans(Pageable pageable, User user) {
        return planRepository.findAllMobilePlans(pageable, user);
    }

    @Transactional(readOnly = true)
    public Page<InternetPlanDetailResponse> getAllInternetPlans(Pageable pageable, User user) {
        return planRepository.findAllInternetPlans(pageable, user);
    }

    @Transactional(readOnly = true)
    public Page<IPTVPlanDetailResponse> getAllIPTVPlans(Pageable pageable, User user) {
        return planRepository.findAllIPTVPlans(pageable, user);
    }

    @Transactional(readOnly = true)
    public AdminPlanDetailResponse getPlanDetail(Long planId) {
        Plan plan = getPlan(planId);
        return new AdminPlanDetailResponse(
            plan.getId(),
            plan.getPlanName(),
            plan.getPlanPrice(),
            plan.getPlanBenefit(),
            plan.getAvailability()
        );
    }

    public PlanDetailResponse getTypedPlanDetail(String type, Long planId) {
        Plan plan = getPlan(planId);
        return PlanResponseFactory.from(plan, false);
    }

    @Transactional
    public AdminPlanDeleteResponse deletePlanById(Long planId) {
        Plan plan = getPlan(planId);
        planRepository.delete(plan);
        return new AdminPlanDeleteResponse(planId);
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

    @Transactional
    public AdminUpdateAllVectorResponse updateAllVector() {
        planVectorJdbcRepository.deleteAll();

        List<Plan> planList = planRepository.findAll();
        for (Plan plan : planList) {
            List<Tag> tagList = planTagRepository.findAllByPlan(plan).stream().map(PlanTag::getTag)
                .toList();
            List<CommunityBenefit> communityBenefitList = planCommunityRepository.findAllByPlan(
                    plan)
                .stream().map(PlanCommunity::getCommunityBenefit).toList();

            String description = createDescription(plan, tagList,
                getPricesGroupedByBenefit(communityBenefitList));
            fastAPIClient.saveVector(plan, description);
        }
        return new AdminUpdateAllVectorResponse(planList.size() + "개의 정보를 갱신하였습니다.");
    }

    public Map<CommunityBenefit, CommunityBenefitPrice> getPricesGroupedByBenefit(
        List<CommunityBenefit> communityBenefitList) {

        List<Long> benefitIds = communityBenefitList.stream()
            .map(CommunityBenefit::getId)
            .toList();

        return communityBenefitPriceRepository.findMaxHeadcountPricesByCommunityBenefitIds(
                benefitIds).stream()
            .collect(Collectors.toMap(CommunityBenefitPrice::getCommunityBenefit, cbp -> cbp));
    }

    private void savePlanTags(List<Tag> tagIdList, Plan plan) {
        List<PlanTag> planTagList = new ArrayList<>();
        for (Tag tag : tagIdList) {
            planTagList.add(new PlanTag(plan, tag));
        }
        planTagRepository.saveAll(planTagList);
    }

    private void saveCommunityBenefits(List<CommunityBenefit> communityBenefitList, Plan plan) {
        List<PlanCommunity> planCommunityList = new ArrayList<>();
        for (CommunityBenefit communityBenefit : communityBenefitList) {
            planCommunityList.add(new PlanCommunity(plan, communityBenefit));
        }
        planCommunityRepository.saveAll(planCommunityList);
    }
}
