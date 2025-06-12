package com.ureca.uplait.domain.allowword.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "허용어 일괄 삭제 요청 DTO")
public class AllowWordDeleteRequest {

    @Schema(description = "삭제할 허용어 ID 목록", example = "[1, 2, 3]")
    private List<Long> allowWordIds;
}
