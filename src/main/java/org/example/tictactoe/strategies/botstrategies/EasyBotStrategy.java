package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.strategies.WinnerChecker;

import java.util.List;
import java.util.Random;

public class EasyBotStrategy implements BotStrategy {
    private final BotPlayer botPlayer;
    private final Random random;

    public EasyBotStrategy(BotPlayer botPlayer) {
        this.botPlayer = botPlayer;
        random = new Random();
    }

    @Override
    public Move calculateNextMove(Board board) {
        List<Cell> emptyCells = board.getBoard().stream()
                .flatMap(List::stream)
                .filter(cell -> cell.getState() == CellState.EMPTY)
                .toList();

        if (!emptyCells.isEmpty()) {
            Cell randomCell = emptyCells.get(random.nextInt(emptyCells.size()));
            return new Move(randomCell, botPlayer);
        }
        return null;
    }
}
