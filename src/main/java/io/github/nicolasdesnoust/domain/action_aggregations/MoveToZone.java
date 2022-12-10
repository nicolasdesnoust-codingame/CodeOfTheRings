package io.github.nicolasdesnoust.domain.action_aggregations;

import io.github.nicolasdesnoust.domain.Direction;
import io.github.nicolasdesnoust.domain.GameState;
import io.github.nicolasdesnoust.domain.Path;

import java.util.stream.IntStream;

public class MoveToZone implements ActionAggregation {

    private final int zoneIndex;

    public MoveToZone(int zoneIndex) {
        this.zoneIndex = zoneIndex;
    }

    @Override
    public void apply(GameState gameState) {
        // System.err.printf("Moving to Zone %s%n", zoneIndex);

        Path path = gameState.calculateShortestPathTo(zoneIndex);
        if (Direction.LEFT.equals(path.getDirection())) {
            IntStream.range(0, path.getNumberOfActions())
                    .forEach(i -> gameState.moveBlubLeft());
        } else {
            IntStream.range(0, path.getNumberOfActions())
                    .forEach(i -> gameState.moveBlubRight());
        }
    }
}
