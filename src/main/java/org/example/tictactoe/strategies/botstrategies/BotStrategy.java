package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.game.Move;

public interface BotStrategy {

    Move calculateNextMove(Board board);

}
