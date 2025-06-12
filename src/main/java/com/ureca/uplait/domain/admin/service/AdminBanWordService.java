package com.ureca.uplait.domain.admin.service;

import com.ureca.uplait.domain.admin.dto.request.AdminBanWordRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminBanWordResponse;
import com.ureca.uplait.domain.banword.entity.BanWord;
import com.ureca.uplait.domain.banword.repository.BanWordRepository;
import com.ureca.uplait.domain.common.validator.CommonValidator;
import com.ureca.uplait.domain.common.validator.WordConflictValidator;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminBanWordService {

    private final BanWordRepository banWordRepository;
    private final WordConflictValidator wordConflictValidator;
    private final CommonValidator commonValidator;

    public AdminBanWordResponse registerBanWord(AdminBanWordRequest request) {
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

    public Page<AdminBanWordResponse> getAllBanWords(Pageable pageable) {
        return banWordRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public Long deleteBanWordById(Long id) {
        BanWord banWord = getBanWordOrThrow(id);
        banWordRepository.delete(banWord);

        return id;
    }

    public void deleteBanWordsByIds(List<Long> ids) {
        List<BanWord> banWords = banWordRepository.findAllById(ids);
        commonValidator.validateAllIdsExist(ids, banWords, ResultCode.BANWORD_NOT_FOUND);
        banWordRepository.deleteAll(banWords);
    }

    public Page<AdminBanWordResponse> searchBanWords(String keyword, Pageable pageable) {
        return banWordRepository.findByBanWordContainingIgnoreCase(keyword, pageable)
                .map(this::toResponse);
    }

    private BanWord getBanWordOrThrow(Long id) {
        return banWordRepository.findById(id)
            .orElseThrow(() -> new GlobalException(ResultCode.BANWORD_NOT_FOUND));
    }

    private AdminBanWordResponse toResponse(BanWord banWord) {
        return new AdminBanWordResponse(
                banWord.getId(),
                banWord.getBanWord(),
                banWord.getCreatedAt()
        );
    }
}
