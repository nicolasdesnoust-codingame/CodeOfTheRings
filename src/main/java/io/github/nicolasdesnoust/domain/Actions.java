package io.github.nicolasdesnoust.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Actions {
    private final List<Action> actionList;

    public Actions() {
        this.actionList = new ArrayList<>();
    }

    public void add(Action action) {
        this.actionList.add(action);
    }

    public String toSymbols() {
        return actionList.stream()
                .map(Action::getSymbol)
                .collect(Collectors.joining(""));
    }
}
