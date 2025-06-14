package com.ureca.uplait.domain.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "허용어 등록 요청 DTO")
public class AdminAllowWordRequest {

    @Schema(description = "등록할 허용어", example = "망")
    @NotBlank(message = "허용어는 공백일 수 없습니다.")
    private String allowWord;
}
