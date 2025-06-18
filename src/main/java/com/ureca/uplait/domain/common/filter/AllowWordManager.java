package com.ureca.uplait.domain.common.filter;

import com.ureca.uplait.domain.allowword.entity.AllowWord;
import com.ureca.uplait.domain.allowword.repository.AllowWordRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AllowWordManager {

    private final AllowWordRepository allowWordRepository;
    private final Preprocessor preprocessor = new Preprocessor();

    private Set<String> allowedWords = new HashSet<>();
    private Set<String> normalizedAllowedWords = new HashSet<>();

    @PostConstruct
    public void init() {
        reload();
    }

    public synchronized void reload() {
        Set<String> loaded = allowWordRepository.findAll()
                .stream()
                .map(AllowWord::getAllowWord)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        allowedWords = Collections.unmodifiableSet(loaded);
        normalizedAllowedWords = Collections.unmodifiableSet(
                loaded.stream().map(preprocessor::normalize).collect(Collectors.toSet()));
    }

    public Set<String> getAll() {
        return allowedWords;
    }

    public Set<String> getAllNormalized() {
        return normalizedAllowedWords;
    }
}

