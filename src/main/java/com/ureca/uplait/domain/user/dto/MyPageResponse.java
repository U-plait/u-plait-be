package com.ureca.uplait.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "마이페이지 응답")
public class MyPageResponse {
    //@Schema()
    private UserProfile userProfile;
    private List<Review>
}
