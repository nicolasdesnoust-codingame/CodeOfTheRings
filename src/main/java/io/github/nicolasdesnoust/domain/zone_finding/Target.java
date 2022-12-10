package io.github.nicolasdesnoust.domain.zone_finding;

import java.util.Objects;

public class Target implements Comparable<Target> {
    private final int zoneIndex;
    private final int numberOfActions;

    public Target(int zoneIndex, int numberOfActions) {
        this.zoneIndex = zoneIndex;
        this.numberOfActions = numberOfActions;
    }

    public int getZoneIndex() {
        return zoneIndex;
    }

    public int getNumberOfActions() {
        return numberOfActions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return zoneIndex == target.zoneIndex && numberOfActions == target.numberOfActions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneIndex, numberOfActions);
    }

    @Override
    public int compareTo(Target other) {
        int numberOfActionsComparison = Integer.compare(this.numberOfActions, other.numberOfActions);

        if (numberOfActionsComparison != 0) {
            return numberOfActionsComparison;
        } else {
            return Integer.compare(this.zoneIndex, other.zoneIndex);
        }
    }
}
