package com.ureca.uplait.domain.admin.service;

import com.ureca.uplait.domain.admin.dto.request.AdminBanWordRequest;
import com.ureca.uplait.domain.admin.dto.response.AdminBanWordResponse;
import com.ureca.uplait.domain.banword.entity.BanWord;
import com.ureca.uplait.domain.banword.repository.BanWordRepository;
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

    public AdminBanWordResponse registerBanWord(AdminBanWordRequest request) {
        String value = request.getBanWord();
        validateNotDuplicated(value);

        BanWord banWord = new BanWord(value);
        banWordRepository.save(banWord);

        return new AdminBanWordResponse(
            banWord.getId(),
            banWord.getBanWord(),
            banWord.getCreatedAt()
        );
    }

    public Page<AdminBanWordResponse> getAllBanWords(Pageable pageable) {
        return banWordRepository.findAll(pageable)
            .map(bw -> new AdminBanWordResponse(
                bw.getId(),
                bw.getBanWord(),
                bw.getCreatedAt()
            ));
    }

    public Long deleteBanWordById(Long id) {
        BanWord banWord = getBanWordOrThrow(id);
        banWordRepository.delete(banWord);

        return id;
    }

    public void deleteBanWordsByIds(List<Long> ids) {
        List<BanWord> banWords = banWordRepository.findAllById(ids);
        validateAllIdsExist(ids, banWords);
        banWordRepository.deleteAll(banWords);
    }

    public Page<AdminBanWordResponse> searchBanWords(String keyword, Pageable pageable) {
        return banWordRepository.findByBanWordContainingIgnoreCase(keyword, pageable)
            .map(bw -> new AdminBanWordResponse(
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
