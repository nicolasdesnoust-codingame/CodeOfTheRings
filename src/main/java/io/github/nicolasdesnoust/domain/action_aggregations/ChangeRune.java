package io.github.nicolasdesnoust.domain.action_aggregations;

import io.github.nicolasdesnoust.domain.Direction;
import io.github.nicolasdesnoust.domain.GameState;
import io.github.nicolasdesnoust.domain.Path;
import io.github.nicolasdesnoust.domain.Rune;

import java.util.stream.IntStream;

public class ChangeRune implements ActionAggregation {

    private final Rune newRune;

    public ChangeRune(Rune newRune) {
        this.newRune = newRune;
    }

    @Override
    public void apply(GameState gameState) {
        // System.err.printf("Changing rune %s by %s%n", gameState.getCurrentRune(), newRune);

        Rune currentRune = gameState.getCurrentRune();
        Path path = currentRune.calculateShortestPathTo(newRune);
        if (Direction.LEFT.equals(path.getDirection())) {
            IntStream.range(0, path.getNumberOfActions())
                    .forEach(i -> gameState.rollsBackRuneLetter());
        } else {
            IntStream.range(0, path.getNumberOfActions())
                    .forEach(i -> gameState.rollRuneLetter());
        }
    }
}
