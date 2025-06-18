package com.ureca.uplait.domain.common.filter;

import com.ureca.uplait.domain.banword.entity.BanWord;
import com.ureca.uplait.domain.banword.repository.BanWordRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FuzzyMatcher {

    private final BanWordRepository banWordRepository;
    private final AllowWordManager allowWords;

    private List<String> banWordList = new ArrayList<>();
    private static final int FUZZY_THRESHOLD = 1; // 허용 거리

    @PostConstruct
    public void init() {
        reload();
    }

    public void reload() {
        this.banWordList = banWordRepository.findAll()
                .stream()
                .map(BanWord::getBanWord)
                .collect(Collectors.toList());
    }

    public boolean isSuspicious(String input) {
        if (input == null || input.isBlank()) return false;

        String[] tokens = input.toLowerCase().split("\\s+");
        Set<String> whiteWords = allowWords.getAll();

        for (String token : tokens) {
            for (String ban : banWordList) {
                if (whiteWords.contains(ban.toLowerCase())) continue;

                if (isFuzzyMatch(token, ban)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFuzzyMatch(String token, String banWord) {
        int distance = levenshteinDistance(token, banWord.toLowerCase());
        int maxLength = Math.max(token.length(), banWord.length());
        int allowedDistance = maxLength <= 4 ? 1 : FUZZY_THRESHOLD;

        return distance <= allowedDistance;
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[a.length()][b.length()];
    }
}