package com.ureca.uplait.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TagListResponse {
    @Schema(description = "태그 List", example = "태그 List")
    List<TagResponse> tagList;
}
