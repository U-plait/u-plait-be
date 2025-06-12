package com.ureca.uplait.domain.banword.service;

import com.ureca.uplait.domain.banword.dto.request.BanWordRequest;
import com.ureca.uplait.domain.banword.dto.response.BanWordResponse;
import com.ureca.uplait.domain.banword.entity.BanWord;
import com.ureca.uplait.domain.banword.repository.BanWordRepository;
import com.ureca.uplait.domain.common.validator.CommonValidator;
import com.ureca.uplait.domain.common.validator.WordConflictValidator;
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
    private final WordConflictValidator wordConflictValidator;
    private final CommonValidator commonValidator;

    public BanWordResponse registerBanWord(BanWordRequest request) {
        String value = request.getBanWord();

        commonValidator.validateNotDuplicated(
                banWordRepository.existsByBanWord(value),
                ResultCode.DUPLICATED_BANWORD
        );

        wordConflictValidator.ensureNotInAllowWords(value);

        BanWord banWord = new BanWord(value);
        banWordRepository.save(banWord);

        return toResponse(banWord);
    }

    public Page<BanWordResponse> getAllBanWords(Pageable pageable) {
        return banWordRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public void deleteBanWordById(Long id) {
        BanWord banWord = getBanWordOrThrow(id);
        banWordRepository.delete(banWord);
    }

    public void deleteBanWordsByIds(List<Long> ids) {
        List<BanWord> banWords = banWordRepository.findAllById(ids);
        commonValidator.validateAllIdsExist(ids, banWords, ResultCode.BANWORD_NOT_FOUND);
        banWordRepository.deleteAll(banWords);
    }

    public Page<BanWordResponse> searchBanWords(String keyword, Pageable pageable) {
        return banWordRepository.findByBanWordContainingIgnoreCase(keyword, pageable)
                .map(this::toResponse);
    }

    private BanWord getBanWordOrThrow(Long id) {
        return banWordRepository.findById(id)
                .orElseThrow(() -> new GlobalException(ResultCode.BANWORD_NOT_FOUND));
    }

    private BanWordResponse toResponse(BanWord banWord) {
        return new BanWordResponse(
                banWord.getId(),
                banWord.getBanWord(),
                banWord.getCreatedAt()
        );
    }
}
