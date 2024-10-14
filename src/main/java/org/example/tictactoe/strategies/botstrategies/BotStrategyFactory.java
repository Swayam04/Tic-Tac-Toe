package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.entities.players.Difficulty;
import org.example.tictactoe.strategies.WinnerChecker;

public class BotStrategyFactory {
    public static BotStrategy createBotStrategy(BotPlayer botPlayer, WinnerChecker winnerChecker) {
        Difficulty difficulty = botPlayer.getDifficulty();
        return switch (difficulty) {
            case EASY -> new EasyBotStrategy(botPlayer);
            case MEDIUM -> new MediumBotStrategy(botPlayer, winnerChecker, new EasyBotStrategy(botPlayer));
            case HARD -> new HardBotStrategy(botPlayer, winnerChecker);
        };
    }
}
