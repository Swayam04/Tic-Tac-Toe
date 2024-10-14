package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.players.BotPlayer;

public class HardBotStrategy implements BotStrategy {
    private  final  BotPlayer botPlayer;

    public HardBotStrategy(BotPlayer botPlayer) {
        this.botPlayer = botPlayer;
    }

    @Override
    public Move calculateNextMove(Board board) {
        return null;
    }
}
