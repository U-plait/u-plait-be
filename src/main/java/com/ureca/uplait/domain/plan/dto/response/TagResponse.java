package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.user.entity.Tag;
import lombok.Getter;

@Getter
public class TagResponse {

    private Long id;
    private String tagName;

    public TagResponse(Tag tag) {
        this.id = tag.getId();
        this.tagName = tag.getName();
    }
}
