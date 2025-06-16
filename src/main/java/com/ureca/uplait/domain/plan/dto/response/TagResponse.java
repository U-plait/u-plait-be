package com.ureca.uplait.domain.plan.dto.response;

import com.ureca.uplait.domain.user.entity.Tag;
import lombok.Getter;

@Getter
public class TagResponse {
    private Long tagId;
    private String tagName;

    public TagResponse(Tag tag) {
        this.tagId = tag.getId();
        this.tagName = tag.getName();
    }
}
