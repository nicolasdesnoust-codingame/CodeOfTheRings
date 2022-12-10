package io.github.nicolasdesnoust.domain.zone_finding;

import io.github.nicolasdesnoust.domain.GameState;

import java.util.List;

public interface ZoneFinder {
    List<Target> find(GameState gameState);
}
