package com.ureca.uplait.domain.plan.dto;

import com.ureca.uplait.domain.plan.dto.request.InternetCreateDto;
import com.ureca.uplait.domain.plan.dto.request.IptvCreateDto;
import com.ureca.uplait.domain.plan.dto.request.MobilePlanCreateDto;
import com.ureca.uplait.domain.plan.dto.resoponse.InternetResponseDto;
import com.ureca.uplait.domain.plan.dto.resoponse.IptvResponseDto;
import com.ureca.uplait.domain.plan.dto.resoponse.MobileResponseDto;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanMapper {

    MobilePlan toCreateMobile(MobilePlanCreateDto dto);

    IPTVPlan toCreateIptv(IptvCreateDto dto);

    InternetPlan toCreateInternet(InternetCreateDto dto);

    MobileResponseDto fromMobileResponse(MobilePlan dto);

    IptvResponseDto fromIptvResponse(IPTVPlan dto);

    InternetResponseDto fromInternetResponse(InternetPlan dto);
}
