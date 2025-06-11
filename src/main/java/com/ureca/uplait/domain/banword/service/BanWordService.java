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

        validateNotDuplicated(value);

        BanWord banWord = banWordMapper.toEntity(request);
        banWordRepository.save(banWord);

        return banWordMapper.toDto(banWord);
    }


    // 금칙어 목록 전체 조회
    public Page<BanWordResponse> getAllBanWords(Pageable pageable) {
        Page<BanWord> results = banWordQueryRepository.findAll(pageable);
        return banWordMapper.toDtoPage(results);
    }


    // 금칙어 단일 삭제
    public void deleteBanWordById(Long id) {
        BanWord banWord = getBanWordOrThrow(id);
        banWordRepository.delete(banWord);
    }


    // 금칙어 일괄 삭제
    public void deleteBanWordsByIds(List<Long> ids) {
        List<BanWord> banWords = banWordRepository.findAllById(ids);

        validateAllIdsExist(ids, banWords);

        banWordRepository.deleteAll(banWords);
    }

    // 금칙어 검색
    public Page<BanWordResponse> searchBanWords(String keyword, Pageable pageable) {
        Page<BanWord> results = banWordQueryRepository.search(keyword, pageable);
        return banWordMapper.toDtoPage(results);
    }



    // 유효성 검사
    private void validateAllIdsExist(List<Long> ids, List<BanWord> banWords) {
        if (banWords.size() != ids.size()) {
            throw new GlobalException(ResultCode.BANWORD_NOT_FOUND);
        }
    }

    private void validateNotDuplicated(String value) {
        if (banWordRepository.existsByBanWord(value)) {
            throw new GlobalException(ResultCode.DUPLICATED_BANWORD);
        }
    }

    private BanWord getBanWordOrThrow(Long id) {
        return banWordRepository.findById(id)
                .orElseThrow(() -> new GlobalException(ResultCode.BANWORD_NOT_FOUND));
    }
}
