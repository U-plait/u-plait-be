package com.ureca.uplait.domain.admin.service;

import com.ureca.uplait.domain.admin.dto.request.AdminAllowWordRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminAllowWordResponse;
import com.ureca.uplait.domain.allowword.entity.AllowWord;
import com.ureca.uplait.domain.allowword.repository.AllowWordRepository;
import com.ureca.uplait.domain.common.filter.AllowWordManager;
import com.ureca.uplait.domain.common.filter.BanWordFilter;
import com.ureca.uplait.domain.common.validator.CommonValidator;
import com.ureca.uplait.domain.common.validator.WordConflictValidator;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAllowWordService {

    private final AllowWordRepository allowWordRepository;
    private final WordConflictValidator wordConflictValidator;
    private final CommonValidator commonValidator;
    private final BanWordFilter banWordFilter;
    private final AllowWordManager allowWordManager;

    @Transactional
    public AdminAllowWordResponse registerAllowWord(AdminAllowWordRequest request) {
        String value = request.getAllowWord();
        commonValidator.validateNotDuplicated(allowWordRepository.existsByAllowWord(value), ResultCode.DUPLICATED_ALLOWWORD);
        wordConflictValidator.ensureNotInBanWords(value);

        AllowWord allowWord = new AllowWord(value);
        allowWordRepository.save(allowWord);

        allowWordManager.reload();
        banWordFilter.reload();

        return toResponse(allowWord);
    }

    public Page<AdminAllowWordResponse> getAllAllowWords(Pageable pageable) {
        return allowWordRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Transactional
    public void deleteAllowWordById(Long id) {
        AllowWord allowWord = getAllowWordOrThrow(id);
        allowWordRepository.delete(allowWord);

        allowWordManager.reload();
        banWordFilter.reload();
    }

    @Transactional
    public void deleteAllowWordsByIds(List<Long> ids) {
        List<AllowWord> allowWords = allowWordRepository.findAllById(ids);
        commonValidator.validateAllIdsExist(ids, allowWords, ResultCode.ALLOWWORD_NOT_FOUND);
        allowWordRepository.deleteAll(allowWords);

        allowWordManager.reload();
        banWordFilter.reload();
    }

    public Page<AdminAllowWordResponse> searchAllowWords(String keyword, Pageable pageable) {
        return allowWordRepository.findByAllowWordContainingIgnoreCase(keyword, pageable)
                .map(this::toResponse);
    }

    private AllowWord getAllowWordOrThrow(Long id) {
        return allowWordRepository.findById(id)
                .orElseThrow(() -> new GlobalException(ResultCode.ALLOWWORD_NOT_FOUND));
    }

    private AdminAllowWordResponse toResponse(AllowWord allowWord) {
        return new AdminAllowWordResponse(
                allowWord.getId(),
                allowWord.getAllowWord(),
                allowWord.getCreatedAt()
        );
    }
}
