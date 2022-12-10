package io.github.nicolasdesnoust.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GameState {
    public static final int NUMBER_OF_ZONES = 30;
    private final List<Zone> zones;
    private int blubPosition;
    private final Actions actionsHistory;

    public GameState() {
        zones = new ArrayList<>(NUMBER_OF_ZONES);
        IntStream.range(0, NUMBER_OF_ZONES)
                .forEach(i -> zones.add(new Zone(i)));
        actionsHistory = new Actions();
    }

    public void moveBlubLeft() {
        blubPosition = blubPosition - 1 < 0 ? NUMBER_OF_ZONES - 1 : blubPosition - 1;
        actionsHistory.add(Action.GO_LEFT);
    }

    public void moveBlubRight() {
        blubPosition = (blubPosition + 1) % NUMBER_OF_ZONES;
        actionsHistory.add(Action.GO_RIGHT);
    }

    public Path calculateShortestPathTo(int runeIndex) {
        if (runeIndex < blubPosition) {
            int numberOfStepsGoingLeft = blubPosition - runeIndex;
            int numberOfStepsGoingRight = NUMBER_OF_ZONES - blubPosition + runeIndex;

            if (numberOfStepsGoingLeft <= numberOfStepsGoingRight) {
                return new Path(Direction.LEFT, numberOfStepsGoingLeft);
            } else {
                return new Path(Direction.RIGHT, numberOfStepsGoingRight);
            }
        } else {
            int numberOfStepsGoingRight = runeIndex - blubPosition;
            int numberOfStepsGoingLeft = blubPosition + NUMBER_OF_ZONES - runeIndex;

            if (numberOfStepsGoingRight <= numberOfStepsGoingLeft) {
                return new Path(Direction.RIGHT, numberOfStepsGoingRight);
            } else {
                return new Path(Direction.LEFT, numberOfStepsGoingLeft);
            }
        }
    }

    public int calculateShortestDistanceToZone(int zoneIndex) {
        if (zoneIndex < blubPosition) {
            int numberOfStepsGoingLeft = blubPosition - zoneIndex;
            int numberOfStepsGoingRight = NUMBER_OF_ZONES - blubPosition + zoneIndex;
            return Math.min(numberOfStepsGoingLeft, numberOfStepsGoingRight);
        } else {
            int numberOfStepsGoingRight = zoneIndex - blubPosition;
            int numberOfStepsGoingLeft = blubPosition + NUMBER_OF_ZONES - zoneIndex;
            return Math.min(numberOfStepsGoingRight, numberOfStepsGoingLeft);
        }
    }

    public void activateCurrentRune() {
        this.actionsHistory.add(Action.ACTIVATE_RUNE);
    }

    public void rollRuneLetter() {
        zones.get(blubPosition).nextRune();
        actionsHistory.add(Action.ROLLS_RUNE_LETTER);
    }

    public void rollsBackRuneLetter() {
        zones.get(blubPosition).previousRune();
        actionsHistory.add(Action.ROLLS_BACK_RUNE_LETTER);
    }

    public Rune getCurrentRune() {
        return zones.get(blubPosition).getRune();
    }

    public List<Zone> getZones() {
        return zones;
    }

    public Actions getActionsHistory() {
        return actionsHistory;
    }
}
