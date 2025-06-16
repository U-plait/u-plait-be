package com.ureca.uplait.domain.admin.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "전체 vector 정보 갱신 DTO")
public class AdminUpdateAllVectorResponse {
    @Schema(description = "응답 메세지", example = "2개의 정보를 갱신하였습니다.")
    String message;
}
