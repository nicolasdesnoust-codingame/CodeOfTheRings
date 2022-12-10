package io.github.nicolasdesnoust.domain;

import java.util.Objects;

public class Zone {
    private Rune rune;
    private final int index;

    public Zone(int index) {
        this.rune = Rune.SPACE;
        this.index = index;
    }

    public void nextRune() {
        rune = rune.next();
    }

    public void previousRune() {
        rune = rune.previous();
    }

    public boolean hasRune(Rune rune) {
        return Objects.equals(this.rune, rune);
    }

    public int getIndex() {
        return index;
    }

    public Rune getRune() {
        return rune;
    }
}
