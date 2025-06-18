package com.ureca.uplait.domain.common.filter;

import java.util.*;

public class AhoCorasickDAT<T> {

    private static final int ROOT = 0;
    private static final int INITIAL_SIZE = 1024;
    private static final int CHAR_SIZE = 128;

    private int[] base;
    private int[] check;
    private int[] fail;
    private List<List<T>> output;
    private final Map<String, T> keywordMap = new HashMap<>();

    private boolean built = false;

    public AhoCorasickDAT() {
        initArrays(INITIAL_SIZE);
    }

    public void add(String word, T value) {
        if (built) throw new IllegalStateException("Cannot add after build()");
        keywordMap.put(word, value);
    }

    public void build() {
        for (Map.Entry<String, T> entry : keywordMap.entrySet()) {
            insert(entry.getKey(), entry.getValue());
        }
        buildFailureLinks();
        built = true;
    }

    private void insert(String word, T value) {
        int current = ROOT;
        for (char ch : word.toCharArray()) {
            int next = getTransition(current, ch);
            if (next == -1) {
                next = createTransition(current, ch);
            }
            current = next;
        }
        if (output.get(current) == null) output.set(current, new ArrayList<>());
        output.get(current).add(value);
    }

    private int createTransition(int parent, char ch) {
        int baseValue = findSafeBase(parent);
        base[parent] = baseValue;

        int index = baseValue + ch;
        ensureCapacity(index + 1);
        check[index] = parent;

        return index;
    }

    private int findSafeBase(int parent) {
        for (int candidate = 1; candidate < base.length; candidate++) {
            boolean conflict = false;
            for (char c = 0; c < CHAR_SIZE; c++) {
                int idx = candidate + c;
                if (idx < check.length && check[idx] != 0) {
                    conflict = true;
                    break;
                }
            }
            if (!conflict) return candidate;
        }
        return base.length;
    }

    private int getTransition(int state, char ch) {
        int index = base[state] + ch;
        if (index < check.length && check[index] == state) return index;
        return -1;
    }

    private void buildFailureLinks() {
        Queue<Integer> queue = new LinkedList<>();
        fail[ROOT] = ROOT;

        for (char ch = 0; ch < CHAR_SIZE; ch++) {
            int next = getTransition(ROOT, ch);
            if (next != -1) {
                fail[next] = ROOT;
                queue.add(next);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (char ch = 0; ch < CHAR_SIZE; ch++) {
                int next = getTransition(current, ch);
                if (next == -1) continue;

                queue.add(next);
                int fallback = fail[current];
                while (fallback != ROOT && getTransition(fallback, ch) == -1) {
                    fallback = fail[fallback];
                }

                int fallbackTransition = getTransition(fallback, ch);
                if (fallbackTransition == -1) fallbackTransition = ROOT;

                fail[next] = fallbackTransition;

                if (output.get(fallbackTransition) != null) {
                    if (output.get(next) == null) output.set(next, new ArrayList<>());
                    output.get(next).addAll(output.get(fallbackTransition));
                }
            }
        }
    }

    public List<Hit<T>> parse(String text) {
        List<Hit<T>> results = new ArrayList<>();
        int state = ROOT;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            while (state != ROOT && getTransition(state, ch) == -1) {
                state = fail[state];
            }

            int next = getTransition(state, ch);
            if (next != -1) state = next;

            List<T> matches = output.get(state);
            if (matches != null) {
                for (T val : matches) {
                    int start = i - val.toString().length() + 1;
                    results.add(new Hit<>(start, i, val));
                }
            }
        }

        return results;
    }

    private void ensureCapacity(int requiredSize) {
        if (requiredSize > base.length) {
            int newSize = Math.max(requiredSize, base.length * 2);
            resizeArrays(newSize);
        }
    }

    private void resizeArrays(int newSize) {
        base = Arrays.copyOf(base, newSize);
        check = Arrays.copyOf(check, newSize);
        fail = Arrays.copyOf(fail, newSize);
        while (output.size() < newSize) output.add(null);
    }

    private void initArrays(int size) {
        base = new int[size];
        check = new int[size];
        fail = new int[size];
        output = new ArrayList<>(size);
        for (int i = 0; i < size; i++) output.add(null);
    }

    public void clear() {
        keywordMap.clear();
        built = false;
        initArrays(INITIAL_SIZE);
    }

    public static class Hit<T> {
        public final int start;
        public final int end;
        public final T value;

        public Hit(int start, int end, T value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}

