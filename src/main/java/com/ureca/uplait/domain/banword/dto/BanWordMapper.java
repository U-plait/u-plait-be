package com.ureca.uplait.domain.banword.dto;

import com.ureca.uplait.domain.banword.dto.request.BanWordRequest;
import com.ureca.uplait.domain.banword.dto.response.BanWordResponse;
import com.ureca.uplait.domain.banword.entity.BanWord;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BanWordMapper {

    // DTO -> Entity
    BanWord toEntity(BanWordRequest request);

    // Entity -> DTO
    BanWordResponse toDto(BanWord banWord);

    List<BanWordResponse> toDtoList(List<BanWord> banWords);

    default Page<BanWordResponse> toDtoPage(Page<BanWord> page) {
        return page.map(this::toDto);
    }

}
