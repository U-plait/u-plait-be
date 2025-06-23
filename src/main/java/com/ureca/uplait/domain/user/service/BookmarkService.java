package com.ureca.uplait.domain.user.service;

import static com.ureca.uplait.global.response.ResultCode.BOOKMARK_NOT_FOUND;
import static com.ureca.uplait.global.response.ResultCode.DUPLICATED_BOOKMARK;
import static com.ureca.uplait.global.response.ResultCode.FORBIDDEN;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.entity.PlanType;
import com.ureca.uplait.domain.user.dto.response.BookmarkListResponse;
import com.ureca.uplait.domain.user.dto.response.BookmarkResponse;
import com.ureca.uplait.domain.user.dto.response.BookmarkResponseFactory;
import com.ureca.uplait.domain.user.dto.response.CreateBookmarkResponse;
import com.ureca.uplait.domain.user.dto.response.DeleteBookmarkResponse;
import com.ureca.uplait.domain.user.entity.Bookmark;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.BookmarkRepository;
import com.ureca.uplait.global.exception.GlobalException;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final EntityManager em;

    @Transactional(readOnly = true)
    public BookmarkListResponse getBookmarks(User user, PlanType planType, int size,
        Long lastBookmarkId) {
        List<BookmarkResponse> bookmarkList = new java.util.ArrayList<>(
            bookmarkRepository.getBookmarksByPlanAndPage(user.getId(), size + 1, lastBookmarkId,
                    planType)
                .stream().map(b -> BookmarkResponseFactory.from(b.getPlan(), b.getId(), true))
                .toList());

        boolean hasNext = (bookmarkList.size() > size);
        if (hasNext) {
            bookmarkList.remove(bookmarkList.size() - 1);
        }

        return new BookmarkListResponse(bookmarkList, hasNext);
    }

    @Transactional
    public CreateBookmarkResponse createBookmark(User user, Long planId) {
        if (bookmarkRepository.existsBookmarkByPlanIdAndUser(planId, user)) {
            throw new GlobalException(DUPLICATED_BOOKMARK);
        }

        Plan planRef = em.getReference(Plan.class, planId);
        Bookmark bookmark = new Bookmark(user, planRef);

        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        return new CreateBookmarkResponse(savedBookmark.getId());
    }

    @Transactional
    public DeleteBookmarkResponse deleteBookmark(User user, Long planId) {
        Bookmark bookmark = findBookmark(user.getId(), planId);
        checkAuthority(user, bookmark);

        long deletedPlanId = bookmark.getPlan().getId();

        bookmarkRepository.delete(bookmark);

        return new DeleteBookmarkResponse(deletedPlanId);
    }

    private void checkAuthority(User user, Bookmark bookmark) {
        if (!bookmark.getUser().getId().equals(user.getId())) {
            throw new GlobalException(FORBIDDEN);
        }
    }

    private Bookmark findBookmark(Long userId, Long planId) {
        return bookmarkRepository.findByUserIdAndPlanId(userId, planId)
            .orElseThrow(() -> new GlobalException(BOOKMARK_NOT_FOUND));
    }
}
