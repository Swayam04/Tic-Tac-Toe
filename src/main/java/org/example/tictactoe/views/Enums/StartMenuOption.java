package org.example.tictactoe.views.Enums;

public enum StartMenuOption {
    START_GAME(1),
    EXIT(2);

    private final int value;

    StartMenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
