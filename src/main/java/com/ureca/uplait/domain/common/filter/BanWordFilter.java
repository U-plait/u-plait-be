package com.ureca.uplait.domain.common.filter;

import com.ureca.uplait.domain.banword.entity.BanWord;
import com.ureca.uplait.domain.banword.repository.BanWordRepository;
import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BanWordFilter {

    private final BanWordRepository banWordRepository;
    private final FuzzyMatcher fuzzyMatcher;
    private final AllowWordManager allowWords;
    private final Preprocessor preprocessor = new Preprocessor();

    private final AhoCorasickDAT<String> trie = new AhoCorasickDAT<>();
    private final AhoCorasickDAT<String> jamoTrie = new AhoCorasickDAT<>();

    private List<String> banWordList = new ArrayList<>();

    @PostConstruct
    public void init() {
        reload();
    }

    public void reload() {
        loadBanWords();
        buildTries();
    }

    public void filter(String input) {
        if (input == null || input.isBlank()) return;

        String normalized = preprocessor.normalize(input);

        for (String allow : allowWords.getAllNormalized()) {
            normalized = normalized.replaceAll(Pattern.quote(allow), "");
        }

        String compacted = normalized.replaceAll("\\s+", "");

        Set<String> detected = detectBanWords(compacted, compacted);

        if (!detected.isEmpty()) {
            throw new GlobalException(ResultCode.BANWORD_INCLUDED);
        }
    }

    private Set<String> detectBanWords(String whitelistCleaned, String normalized) {
        Set<String> hits = new HashSet<>();

        detectFromTrie(whitelistCleaned, hits, trie);
        detectFromTrie(JamoUtil.toJamo(whitelistCleaned), hits, jamoTrie);

        boolean hasBanWord = hits.stream().anyMatch(banWordList::contains);

        if (!hasBanWord &&
                (fuzzyMatcher.isSuspicious(normalized) || fuzzyMatcher.isSuspicious(JamoUtil.toJamo(normalized)))) {
            hits.add("fuzzy_match");
        }

        return hits;
    }

    private void detectFromTrie(String text, Set<String> hits, AhoCorasickDAT<String> trie) {
        for (AhoCorasickDAT.Hit<String> hit : trie.parse(text)) {
            hits.add(hit.value);
        }
    }

    private void loadBanWords() {
        this.banWordList = banWordRepository.findAll()
                .stream()
                .map(BanWord::getBanWord)
                .collect(Collectors.toList());
    }

    private void buildTries() {
        trie.clear();
        jamoTrie.clear();

        if(banWordList.isEmpty()){
            return;
        }

        for (String word : banWordList) {
            trie.add(word, word);
            jamoTrie.add(JamoUtil.toJamo(word), word);
        }

        trie.build();
        jamoTrie.build();
    }
}
