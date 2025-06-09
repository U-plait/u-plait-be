package com.ureca.uplait.domain.plan.service;

import com.ureca.uplait.domain.plan.dto.PlanMapper;
import com.ureca.uplait.domain.plan.dto.request.InternetCreateDto;
import com.ureca.uplait.domain.plan.dto.request.IptvCreateDto;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateDto;
import com.ureca.uplait.domain.plan.dto.resoponse.PlanDetailResponse;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminPlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    //생성
    @Transactional
    public Long createMobilePlan(MobilePlanCreateDto dto) {
        //validate -> 해주고 duplicate 된 것인지 아닌지
        validateDuplicatePlanName(dto.planName());

        MobilePlan mobilePlan = planMapper.toCreateMobile(dto);

        MobilePlan mp = (MobilePlan) planRepository.save(mobilePlan);

        return mp.getId();
    }

    @Transactional
    public Long createIptvPlan(IptvCreateDto dto) {
        validateDuplicatePlanName(dto.planName());

        IPTVPlan iptvPlan = planMapper.toCreateIptv(dto);

        IPTVPlan ipPlan = (IPTVPlan) planRepository.save(iptvPlan);

        return ipPlan.getId();
    }

    @Transactional
    public Long createInternetPlan(InternetCreateDto dto) {
        validateDuplicatePlanName(dto.planName());

        InternetPlan internetPlan = planMapper.toCreateInternet(dto);

        InternetPlan interPlan = (InternetPlan) planRepository.save(internetPlan);

        return interPlan.getId();
    }

    @Transactional(readOnly = true)
    public PlanDetailResponse getPlanDetail(Long id) {
        Plan plan = planRepository.findById(id)
            .orElseThrow(() -> new GlobalException(ResultCode.NOT_FOUND_PLAN));

        System.out.println("plan.getClass() = " + plan.getClass());

        if (plan instanceof MobilePlan mobilePlan) {
            return planMapper.fromMobileResponse(mobilePlan);
        } else if (plan instanceof InternetPlan internetPlan) {
            return planMapper.fromInternetResponse(internetPlan);
        } else if (plan instanceof IPTVPlan iptvPlan) {
            return planMapper.fromIptvResponse(iptvPlan);
        }

        throw new GlobalException(ResultCode.NOT_FOUND_PLAN_TYPE);
    }

    private void validateDuplicatePlanName(String planName) {
        if (planRepository.existsByPlanName(planName)) {
            throw new GlobalException(ResultCode.DUPLICATE_PLAN_NAME);
        }
    }

//    private void validatePrice(Integer price) {
//        if(planRepository.)
//    }

    //등록할때 고려해야될사항은 1. 중복된 이름인가,
}
