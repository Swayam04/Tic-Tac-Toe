package org.example.tictactoe.Views.Enums;

public enum PlayerOption {
    Human_Player(1),
    Bot_Player(2);

    private final int value;

    PlayerOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
