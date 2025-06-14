package com.ureca.uplait.domain.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "금칙어 일괄 삭제 요청 DTO")
public class AdminBanWordDeleteRequest {

    @Schema(description = "삭제할 금칙어 ID 목록", example = "[1, 2, 3]")
    private List<Long> banWordIds;
}
