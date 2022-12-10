package io.github.nicolasdesnoust.domain;

public class Path {
    private final Direction direction;
    private final int numberOfActions;

    public Path(Direction direction, int numberOfActions) {
        this.direction = direction;
        this.numberOfActions = numberOfActions;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getNumberOfActions() {
        return numberOfActions;
    }
}
