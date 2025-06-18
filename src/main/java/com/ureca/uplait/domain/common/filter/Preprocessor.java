package com.ureca.uplait.domain.common.filter;

public class Preprocessor {

    public String normalize(String input) {
        if (input == null || input.isBlank()) return "";

        String result = input.toLowerCase();

        result = result.replaceAll("[^가-힣ㄱ-ㅎㅏ-ㅣa-z\\s]", "");

        result = result.replaceAll("(.)\\1{2,}", "$1$1");

        result = result.replaceAll("\\s+", " ").trim();

        return result;
    }
}
