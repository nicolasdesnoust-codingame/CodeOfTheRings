package io.github.nicolasdesnoust.domain;

public enum Action {
    GO_RIGHT(">"),
    GO_LEFT("<"),
    ROLLS_RUNE_LETTER("+"),
    ROLLS_BACK_RUNE_LETTER("-"),
    ACTIVATE_RUNE(".");

    private final String symbol;

    Action(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
