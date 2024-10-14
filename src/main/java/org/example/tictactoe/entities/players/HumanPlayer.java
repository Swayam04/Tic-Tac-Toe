package org.example.tictactoe.entities.players;

import org.example.tictactoe.entities.game.Symbol;

public class HumanPlayer implements Player {
    private final Symbol symbol;
    private final String name;

    public HumanPlayer(Symbol symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;
    }
}
