package com.ureca.uplait.domain.common.filter;

public class JamoUtil {

    private static final char HANGUL_BASE = 0xAC00;
    private static final char HANGUL_END = 0xD7A3;
    private static final int INITIAL_COUNT = 19;
    private static final int MEDIAL_COUNT = 21;
    private static final int FINAL_COUNT = 28;

    private static final char[] INITIALS = {
            'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ',
            'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ',
            'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };

    private static final char[] MEDIALS = {
            'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ',
            'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ',
            'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ',
            'ㅡ', 'ㅢ', 'ㅣ'
    };

    private static final char[] FINALS = {
            0x0000, 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ',
            'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ',
            'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ',
            'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ',
            'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    };

    public static String toJamo(String input) {
        if (input == null || input.isBlank()) return "";

        StringBuilder sb = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (isHangulSyllable(ch)) {
                int syllableIndex = ch - HANGUL_BASE;

                int initialIndex = syllableIndex / (MEDIAL_COUNT * FINAL_COUNT);
                int medialIndex = (syllableIndex % (MEDIAL_COUNT * FINAL_COUNT)) / FINAL_COUNT;
                int finalIndex = syllableIndex % FINAL_COUNT;

                sb.append(INITIALS[initialIndex]);
                sb.append(MEDIALS[medialIndex]);

                if (finalIndex != 0) {
                    sb.append(FINALS[finalIndex]);
                }
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    private static boolean isHangulSyllable(char ch) {
        return ch >= HANGUL_BASE && ch <= HANGUL_END;
    }
}
