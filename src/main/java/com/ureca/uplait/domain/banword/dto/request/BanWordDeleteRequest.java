package com.ureca.uplait.domain.banword.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class BanWordDeleteRequest {
    private List<Long> banWordIds;
}
