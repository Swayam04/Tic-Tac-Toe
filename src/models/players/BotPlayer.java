package models.players;

import models.game.Symbol;

public class BotPlayer implements  Player {
    private final String name;
    private final Symbol symbol;
    private final Difficulty difficulty;

    public BotPlayer(Symbol symbol, Difficulty difficulty) {
        this.symbol = symbol;
        this.difficulty = difficulty;
        this.name = difficulty.name() + "Bot";
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;
    }

    public Difficulty getDifficulty() {
        return  this.difficulty;
    }
}
