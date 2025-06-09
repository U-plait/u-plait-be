package com.ureca.uplait.domain.banword.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BanWordResponse {
    private Long id;
    private String banWord;
    private String createdAt;
    private String updatedAt;
}
