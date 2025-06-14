package com.ureca.uplait.domain.user.service;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.entity.PlanType;
import com.ureca.uplait.domain.user.dto.response.*;
import com.ureca.uplait.domain.user.entity.Bookmark;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.BookmarkRepository;
import com.ureca.uplait.global.exception.GlobalException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ureca.uplait.global.response.ResultCode.*;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final EntityManager em;

    /**
     * 즐겨찾기 조회
     */
    @Transactional(readOnly = true)
    public BookmarkListResponse getBookmarks(User user, PlanType planType, int size, Long lastBookmarkId) {
        List<BookmarkResponse> bookmarkList = new java.util.ArrayList<>(bookmarkRepository.getBookmarksByPlanAndPage(user.getId(),size + 1, lastBookmarkId, planType)
            .stream().map(b -> BookmarkResponseFactory.from(b.getPlan(), b.getId(), true))
            .toList());

        // 다음 페이지 확인 및 반환값 조정
        boolean hasNext = (bookmarkList.size() > size);
        if (hasNext) bookmarkList.remove(bookmarkList.size() - 1);

        return new BookmarkListResponse(bookmarkList, hasNext);
    }

    /**
     * 즐겨찾기 생성
     */
    @Transactional
    public CreateBookmarkResponse createBookmark(User user, Long planId) {
        // 중복 검사
        if(bookmarkRepository.existsBookmarkByPlanId(planId)) {
            throw new GlobalException(DUPLICATED_BOOKMARK);
        }

        Plan planRef = em.getReference(Plan.class, planId);
        Bookmark bookmark = new Bookmark(user, planRef);

        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        return new CreateBookmarkResponse(savedBookmark.getId());
    }

    /**
     * 즐겨찾기 삭제
     */
    @Transactional
    public DeleteBookmarkResponse deleteBookmark(User user, Long bookmarkId) {
        // 권환 확인
        Bookmark bookmark = findBookmark(bookmarkId);
        checkAuthority(user, bookmark);

        // 즐겨찾기 제거
        bookmarkRepository.delete(bookmark);

        return new DeleteBookmarkResponse();
    }

    private void checkAuthority(User user, Bookmark bookmark) {
        if (!bookmark.getUser().getId().equals(user.getId())) {
            throw new GlobalException(FORBIDDEN);
        }
    }

    private Bookmark findBookmark(Long bookmarkId) {
        return bookmarkRepository.findById(bookmarkId).orElseThrow(() -> new GlobalException(BOOKMARK_NOT_FOUND));
    }
}
