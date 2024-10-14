package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.players.BotPlayer;

import java.util.ArrayList;
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
        List<Cell> emptyCells = new ArrayList<>();
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getState() == CellState.EMPTY) {
                    emptyCells.add(cell);
                }
            }
        }
        if (!emptyCells.isEmpty()) {
            Cell randomCell = emptyCells.get(random.nextInt(emptyCells.size()));
            return new Move(randomCell, botPlayer);
        }
        return null;
    }
}
