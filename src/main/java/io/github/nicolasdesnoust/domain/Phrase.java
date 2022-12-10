package io.github.nicolasdesnoust.domain;

import java.util.*;

public class Phrase {
    private final Queue<String> lettersLeft;

    public Phrase(String rawPhrase) {
        this.lettersLeft = new LinkedList<>(Arrays.asList(rawPhrase.split("")));
    }

    public String peekNextLetter() {
        return lettersLeft.peek();
    }

    public boolean hasLetters() {
        return !lettersLeft.isEmpty();
    }

    public List<String> getLettersLeft() {
        return new ArrayList<>(lettersLeft);
    }

    public void removeCurrentLetter() {
        lettersLeft.remove();
    }
}
