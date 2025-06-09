package com.ureca.uplait.domain.mypage.service;

import com.ureca.uplait.domain.mypage.dto.MyPageResponse;
import org.springframework.stereotype.Service;

@Service
public interface MyPageService {

    MyPageResponse getMyPage(Long userId);
}
