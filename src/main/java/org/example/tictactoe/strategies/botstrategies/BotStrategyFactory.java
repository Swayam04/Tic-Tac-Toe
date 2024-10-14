package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.entities.players.Difficulty;

public class BotStrategyFactory {
    public static BotStrategy createBotStrategy(BotPlayer botPlayer) {
        Difficulty difficulty = botPlayer.getDifficulty();
        return switch (difficulty) {
            case EASY -> new EasyBotStrategy(botPlayer);
            case MEDIUM -> new MediumBotStrategy(botPlayer);
            case HARD -> new HardBotStrategy(botPlayer);
        };
    }
}
