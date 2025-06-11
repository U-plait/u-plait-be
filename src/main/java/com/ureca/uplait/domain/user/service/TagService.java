package com.ureca.uplait.domain.user.service;

import com.ureca.uplait.domain.user.dto.response.TagListResponse;
import com.ureca.uplait.domain.user.dto.response.TagResponse;
import com.ureca.uplait.domain.user.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    /**
     * Tag 전체 조회
     */
    public TagListResponse getTags() {
        return new TagListResponse(tagRepository.findAll().stream().map(TagResponse::new).toList());
    }
}
