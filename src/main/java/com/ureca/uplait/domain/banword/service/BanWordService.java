package com.ureca.uplait.domain.banword.service;

import com.ureca.uplait.domain.banword.dto.BanWordMapper;
import com.ureca.uplait.domain.banword.dto.request.BanWordRequest;
import com.ureca.uplait.domain.banword.dto.response.BanWordResponse;
import com.ureca.uplait.domain.banword.entity.BanWord;
import com.ureca.uplait.domain.banword.repository.BanWordQueryRepository;
import com.ureca.uplait.domain.banword.repository.BanWordRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BanWordService {
    private final BanWordRepository banWordRepository;
    private final BanWordQueryRepository banWordQueryRepository;
    private final BanWordMapper banWordMapper;

    // 금칙어 등록
    public BanWordResponse registerBanWord(BanWordRequest request) {
        String value = request.banWord();

        // 금칙어 입력 값이 잘못된 경우
        if (value == null || value.trim().isEmpty()) {
            throw new GlobalException(ResultCode.INVALID_BANWORD_INPUT);
        }

        // 이미 등록된 금칙어일 경우
        if (banWordRepository.existsByBanWord(value)) {
            throw new GlobalException(ResultCode.DUPLICATED_BANWORD);
        }

        BanWord banWord = banWordMapper.toEntity(request);
        banWordRepository.save(banWord);

        return banWordMapper.toDto(banWord);
    }


    // 금칙어 목록 전체 조회
    public List<BanWordResponse> getAllBanWords() {
        List<BanWord> list = banWordRepository.findAll();
        return banWordMapper.toDtoList(list);
    }


    // 금칙어 단일 삭제
    public void deleteBanWordById(Long id) {
        BanWord banWord = banWordRepository.findById(id)
                .orElseThrow(() -> new GlobalException(ResultCode.NOT_FOUND_BANWORD));
        banWordRepository.delete(banWord);
    }


    // 금칙어 일괄 삭제
    public void deleteBanWordsByIds(List<Long> ids) {
        List<BanWord> banWords = banWordRepository.findAllById(ids);

        if (banWords.size() != ids.size()) {
            throw new GlobalException(ResultCode.NOT_FOUND_BANWORD);
        }

        banWordRepository.deleteAll(banWords);
    }

    // 금칙어 검색
    public Page<BanWordResponse> searchBanWords(String keyword, Pageable pageable) {
        Page<BanWord> results = banWordQueryRepository.search(keyword, pageable);
        return banWordMapper.toDtoPage(results);
    }
}
