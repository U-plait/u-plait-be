package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.InternetPlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.request.PlanCreateRequest;
import com.ureca.uplait.domain.plan.dto.response.PlanDetailAdminResponse;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.user.entity.PlanTag;
import com.ureca.uplait.domain.user.entity.Tag;
import com.ureca.uplait.domain.user.repository.PlanTagRepository;
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
    private final EntityManager em;

    @Transactional
    public Long createMobilePlan(MobilePlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        // 요금제 정보 저장
        MobilePlan plan = planRepository.save(request.toMobile());

        // 태그 정보 저장
        savePlanTags(request, plan);

        return plan.getId();
    }

    @Transactional
    public Long createInternetPlan(InternetPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        // 요금제 정보 저장
        InternetPlan plan = planRepository.save(request.toInternet());

        // 태그 정보 저장
        savePlanTags(request, plan);

        return plan.getId();
    }

    @Transactional
    public Long createIptvPlan(IPTVPlanCreateRequest request) {
        validateDuplicatePlanName(request.getPlanName());

        // 요금제 정보 저장
        IPTVPlan plan = planRepository.save(request.toIPTV());

        // 태그 정보 저장
        savePlanTags(request, plan);

        return plan.getId();
    }

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

    @Transactional(readOnly = true)
    public PlanDetailAdminResponse getPlanDetail(Long planId) {
        Plan plan = getPlan(planId);
        return new PlanDetailAdminResponse(plan);
    }

    private void savePlanTags(PlanCreateRequest request, Plan plan) {
        List<PlanTag> planTagList = new ArrayList<>();
        for (Long tagId : request.getTagIdList()) {
            Tag tag = em.getReference(Tag.class, tagId);
            planTagList.add(new PlanTag(plan, tag));
        }
        planTagRepository.saveAll(planTagList);
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
