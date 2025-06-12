package com.ureca.uplait.domain.banword.service;

import com.ureca.uplait.domain.banword.dto.request.BanWordRequest;
import com.ureca.uplait.domain.banword.dto.response.BanWordResponse;
import com.ureca.uplait.domain.banword.entity.BanWord;
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

    public BanWordResponse registerBanWord(BanWordRequest request) {
        String value = request.getBanWord();
        validateNotDuplicated(value);

        BanWord banWord = new BanWord(value);
        banWordRepository.save(banWord);

        return new BanWordResponse(
                banWord.getId(),
                banWord.getBanWord(),
                banWord.getCreatedAt()
        );
    }

    public Page<BanWordResponse> getAllBanWords(Pageable pageable) {
        return banWordRepository.findAll(pageable)
                .map(bw -> new BanWordResponse(
                        bw.getId(),
                        bw.getBanWord(),
                        bw.getCreatedAt()
                ));
    }

    public void deleteBanWordById(Long id) {
        BanWord banWord = getBanWordOrThrow(id);
        banWordRepository.delete(banWord);
    }

    public void deleteBanWordsByIds(List<Long> ids) {
        List<BanWord> banWords = banWordRepository.findAllById(ids);
        validateAllIdsExist(ids, banWords);
        banWordRepository.deleteAll(banWords);
    }

    public Page<BanWordResponse> searchBanWords(String keyword, Pageable pageable) {
        return banWordRepository.findByBanWordContainingIgnoreCase(keyword, pageable)
                .map(bw -> new BanWordResponse(
                        bw.getId(),
                        bw.getBanWord(),
                        bw.getCreatedAt()
                ));
    }

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
