package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.strategies.WinnerChecker;

public class HardBotStrategy implements BotStrategy {
    private  final  BotPlayer botPlayer;
    private  final  WinnerChecker winnerChecker;

    public HardBotStrategy(BotPlayer botPlayer, WinnerChecker winnerChecker) {
        this.botPlayer = botPlayer;
        this.winnerChecker = winnerChecker;
    }

    @Override
    public Move calculateNextMove(Board board) {
        return null;
    }
}
