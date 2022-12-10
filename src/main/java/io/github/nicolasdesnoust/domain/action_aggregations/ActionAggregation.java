package io.github.nicolasdesnoust.domain.action_aggregations;


import io.github.nicolasdesnoust.domain.GameState;

public interface ActionAggregation {
    void apply(GameState gameState);
}
