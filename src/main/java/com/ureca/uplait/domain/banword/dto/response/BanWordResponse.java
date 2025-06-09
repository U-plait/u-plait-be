package com.ureca.uplait.domain.banword.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
public class BanWordResponse {
    private Long id;
    private String banWord;
    private LocalDateTime createdAt;
}
