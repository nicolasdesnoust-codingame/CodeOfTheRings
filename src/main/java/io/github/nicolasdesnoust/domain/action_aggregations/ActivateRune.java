package io.github.nicolasdesnoust.domain.action_aggregations;

import io.github.nicolasdesnoust.domain.GameState;

public class ActivateRune implements ActionAggregation {

    @Override
    public void apply(GameState gameState) {
        // System.err.printf("Activating rune %s%n", gameState.getCurrentRune());
        gameState.activateCurrentRune();
    }
}
