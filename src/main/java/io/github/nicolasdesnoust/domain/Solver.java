package io.github.nicolasdesnoust.domain;

import io.github.nicolasdesnoust.domain.action_aggregations.ActivateRune;
import io.github.nicolasdesnoust.domain.action_aggregations.ChangeRune;
import io.github.nicolasdesnoust.domain.action_aggregations.MoveToZone;
import io.github.nicolasdesnoust.domain.zone_finding.MostEfficientUnusedZoneFinder;
import io.github.nicolasdesnoust.domain.zone_finding.ZonesWithRuneFinder;
import io.github.nicolasdesnoust.domain.zone_finding.Target;

import java.util.*;

public class Solver {

    public Actions solve(Phrase magicPhrase) {
        GameState gameState = new GameState();

        while (magicPhrase.hasLetters()) {
            handleCurrentLetter(magicPhrase, gameState);
            magicPhrase.removeCurrentLetter();
        }

        return gameState.getActionsHistory();
    }

    private void handleCurrentLetter(Phrase magicPhrase, GameState gameState) {
        Rune rune = Rune.fromString(magicPhrase.peekNextLetter());
        int runeIndex = findNextRuneIndex(magicPhrase, gameState, rune);

        new MoveToZone(runeIndex).apply(gameState);
        new ChangeRune(rune).apply(gameState);
        new ActivateRune().apply(gameState);
    }

    /**
     * Je ne veux pas de doublons (une zone une seule fois)                     OK
     * Je veux pouvoir retirer une zone, changer son coût et la réinsérer       FAISABLE
     * Je veux pouvoir récupérer la zone avec le plus petit coût                OK
     */
    private int findNextRuneIndex(Phrase magicPhrase, GameState gameState, Rune rune) {

        TreeSet<Target> numberOfActionsPerZone = new TreeSet<>();

        numberOfActionsPerZone.addAll(new ZonesWithRuneFinder(rune).find(gameState));
        numberOfActionsPerZone.addAll(new MostEfficientUnusedZoneFinder(magicPhrase, rune).find(gameState));

        if (numberOfActionsPerZone.isEmpty()) {
            return 0;
        } else {
            return numberOfActionsPerZone.first().getZoneIndex();
        }
    }
}
