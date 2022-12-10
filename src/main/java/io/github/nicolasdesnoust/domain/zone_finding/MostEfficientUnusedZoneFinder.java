package io.github.nicolasdesnoust.domain.zone_finding;

import io.github.nicolasdesnoust.domain.*;

import java.util.*;

public class MostEfficientUnusedZoneFinder implements ZoneFinder {

    private final Phrase magicPhrase;
    private final Rune rune;

    public MostEfficientUnusedZoneFinder(Phrase magicPhrase, Rune rune) {
        this.magicPhrase = magicPhrase;
        this.rune = rune;
    }

    @Override
    public List<Target> find(GameState gameState) {
        Optional<Target> target = findMostEfficientUnusedZone(rune, magicPhrase, gameState);

        return target.map(Collections::singletonList)
                .orElse(Collections.emptyList());
    }

    private Optional<Target> findMostEfficientUnusedZone(Rune rune, Phrase magicPhrase, GameState gameState) {
        Map<Integer, Integer> actionCounts = new HashMap<>();
        Set<String> lettersLeft = new HashSet<>(magicPhrase.getLettersLeft());

        for (Zone zone : gameState.getZones()) {
            if (lettersLeft.contains(zone.getRune().toString())) {
                continue;
            }

            actionCounts.merge(zone.getIndex(), gameState.calculateShortestDistanceToZone(zone.getIndex()), Integer::sum);
            actionCounts.merge(zone.getIndex(), rune.calculateShortestPathTo(zone.getRune()).getNumberOfActions(), Integer::sum);
        }

        return actionCounts.entrySet().stream()
                .min(Comparator.comparingInt(Map.Entry::getValue))
                .map(entry -> new Target(entry.getKey(), entry.getValue()));
    }
}
