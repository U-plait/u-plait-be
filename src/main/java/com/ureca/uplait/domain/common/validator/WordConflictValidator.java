package com.ureca.uplait.domain.common.validator;

import com.ureca.uplait.domain.allowword.repository.AllowWordRepository;
import com.ureca.uplait.domain.banword.repository.BanWordRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WordConflictValidator {
    private final BanWordRepository banWordRepository;
    private final AllowWordRepository allowWordRepository;

    public void ensureNotInBanWords(String word) {
        if (banWordRepository.existsByBanWord(word)) {
            throw new GlobalException(ResultCode.WORD_ALREADY_EXISTS_IN_BAN_LIST);
        }
    }

    public void ensureNotInAllowWords(String word) {
        if (allowWordRepository.existsByAllowWord(word)) {
            throw new GlobalException(ResultCode.WORD_ALREADY_EXISTS_IN_ALLOW_LIST);
        }
    }
}
