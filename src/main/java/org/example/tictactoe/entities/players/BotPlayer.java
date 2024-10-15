package org.example.tictactoe.entities.players;

import org.example.tictactoe.entities.game.Symbol;
import org.example.tictactoe.strategies.botstrategies.BotStrategy;
import org.example.tictactoe.strategies.botstrategies.BotStrategyFactory;

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

    public BotStrategy getStrategy() {
        return BotStrategyFactory.createBotStrategy(this);
    }
}
