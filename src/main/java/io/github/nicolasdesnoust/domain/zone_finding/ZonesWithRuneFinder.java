package io.github.nicolasdesnoust.domain.zone_finding;

import io.github.nicolasdesnoust.domain.GameState;
import io.github.nicolasdesnoust.domain.Rune;
import io.github.nicolasdesnoust.domain.Zone;

import java.util.List;
import java.util.stream.Collectors;

public class ZonesWithRuneFinder implements ZoneFinder {

    private final Rune rune;

    public ZonesWithRuneFinder(Rune rune) {
        this.rune = rune;
    }

    @Override
    public List<Target> find(GameState gameState) {
        return gameState.getZones().stream()
                .filter(zone -> zone.hasRune(rune))
                .map(zone -> toTarget(gameState, zone))
                .collect(Collectors.toList());
    }

    private Target toTarget(GameState gameState, Zone zone) {
        int numberOfActions = gameState.calculateShortestDistanceToZone(zone.getIndex());
        return new Target(zone.getIndex(), numberOfActions);
    }
}
