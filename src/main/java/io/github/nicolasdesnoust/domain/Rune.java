package io.github.nicolasdesnoust.domain;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Rune {

    A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, SPACE;

    private static final List<Rune> ORDERED_VALUES = new ArrayList<>(EnumSet.allOf(Rune.class));

    public static Rune fromString(String letter) {
        if (" ".equals(letter)) {
            return Rune.SPACE;
        }
        return Enum.valueOf(Rune.class, letter.toUpperCase());
    }

    public Path calculateShortestPathTo(Rune otherRune) {
        int numberOfValues = ORDERED_VALUES.size();
        if (otherRune.isAtLeftOf(this)) {
            int numberOfStepsGoingLeft = this.ordinal() - otherRune.ordinal();
            int numberOfStepsGoingRight = numberOfValues - this.ordinal() + otherRune.ordinal();

            if (numberOfStepsGoingLeft <= numberOfStepsGoingRight) {
                return new Path(Direction.LEFT, numberOfStepsGoingLeft);
            } else {
                return new Path(Direction.RIGHT, numberOfStepsGoingRight);
            }
        } else {
            int numberOfStepsGoingRight = otherRune.ordinal() - this.ordinal();
            int numberOfStepsGoingLeft = this.ordinal() + numberOfValues - otherRune.ordinal();

            if (numberOfStepsGoingRight <= numberOfStepsGoingLeft) {
                return new Path(Direction.RIGHT, numberOfStepsGoingRight);
            } else {
                return new Path(Direction.LEFT, numberOfStepsGoingLeft);
            }
        }
    }

    private boolean isAtLeftOf(Rune otherRune) {
        return this.ordinal() < otherRune.ordinal();
    }

    public Rune next() {
        int nextIndex = (this.ordinal() + 1) % ORDERED_VALUES.size();
        return ORDERED_VALUES.get(nextIndex);
    }

    public Rune previous() {
        int previousIndex = this.ordinal() - 1 >= 0 ? this.ordinal() - 1 : ORDERED_VALUES.size() - 1;
        return ORDERED_VALUES.get(previousIndex);
    }
}
