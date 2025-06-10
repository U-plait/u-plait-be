package com.ureca.uplait.domain.banword.dto;

import com.ureca.uplait.domain.banword.dto.request.BanWordRequest;
import com.ureca.uplait.domain.banword.dto.response.BanWordResponse;
import com.ureca.uplait.domain.banword.entity.BanWord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BanWordMapper {

    // DTO -> Entity
    BanWord toEntity(BanWordRequest request);

    // Entity -> DTO
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    BanWordResponse toDto(BanWord banWord);

    List<BanWordResponse> toDtoList(List<BanWord> banWords);

}
