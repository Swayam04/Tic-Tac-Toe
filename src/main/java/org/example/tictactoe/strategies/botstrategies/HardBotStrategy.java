package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.controllers.MoveController;
import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.game.Symbol;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.entities.players.HumanPlayer;
import org.example.tictactoe.entities.players.Player;
import org.example.tictactoe.strategies.StateChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HardBotStrategy implements BotStrategy {
    private final BotPlayer maximizingPlayer;
    private final Player minimizingPlayer;

    public HardBotStrategy(BotPlayer botPlayer) {
        this.maximizingPlayer = botPlayer;
        Symbol symbol = maximizingPlayer.getSymbol() == Symbol.X ? Symbol.O : Symbol.X;
        this.minimizingPlayer = new HumanPlayer(symbol, "tempPlayer");
    }

    @Override
    public Move calculateNextMove(Board board) {
        int[] optimalMove = minimax(board, 0, maximizingPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE);
        Cell cell = board.getBoard().get(optimalMove[1]).get(optimalMove[2]);
        return new Move(cell, maximizingPlayer);
    }

    private int[] minimax(Board board, int depth, Player currentPlayer, int alpha, int beta) {
        List<Cell> emptyCells = new ArrayList<>();
        for (List<Cell> row : board.getBoard()) {
            for (Cell cell : row) {
                if (cell.getState() == CellState.EMPTY) {
                    emptyCells.add(cell);
                }
            }
        }
        if (StateChecker.checkWinner(board, currentPlayer.getSymbol())) {
            return new int[]{(currentPlayer == maximizingPlayer ? 20 - depth : depth - 20), -1, -1};
        }
        if (emptyCells.isEmpty()) {
            return new int[]{0, -1, -1};
        }
        int bestScore = currentPlayer == minimizingPlayer ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        int row = -1;
        int col = -1;
        for (Cell cell : emptyCells) {
            Move tempMove = new Move(cell, currentPlayer);
            MoveController.makeMove(tempMove);

            try {
                int[] result = minimax(board, depth + 1,
                        currentPlayer == maximizingPlayer ? minimizingPlayer : maximizingPlayer, alpha, beta);
                int adjustedScore = result[0];
                if (adjustedScore == 0) {
                    int openPaths = countOpenPaths(board, cell.getRow(), cell.getColumn(), currentPlayer.getSymbol());
                    adjustedScore += (currentPlayer == maximizingPlayer) ? openPaths * (board.getSize() - depth)
                            : -openPaths * depth;
                }
                if (currentPlayer == maximizingPlayer) {
                    if (adjustedScore > bestScore) {
                        bestScore = adjustedScore;
                        row = cell.getRow();
                        col = cell.getColumn();
                    }
                    alpha = Math.max(alpha, bestScore);
                } else {
                    if (adjustedScore < bestScore) {
                        bestScore = adjustedScore;
                        row = cell.getRow();
                        col = cell.getColumn();
                    }
                    beta = Math.min(beta, bestScore);
                }
                if (alpha >= beta) {
                    break;
                }
            } finally {
                MoveController.undoMove(tempMove);
            }
        }
        return new int[]{bestScore, row, col};
    }



    private int countOpenPaths(Board board, int row, int col, Symbol symbol) {
        int count = 0;
        int size = board.getSize();

        if (isPathOpen(board, i -> board.getBoard().get(row).get(i), symbol)) count++;

        if (isPathOpen(board, i -> board.getBoard().get(i).get(col), symbol)) count++;

        if (row == col) {
            if (isPathOpen(board, i -> board.getBoard().get(i).get(i), symbol)) count++;
        }

        if (row + col == size - 1) {
            if (isPathOpen(board, i -> board.getBoard().get(i).get(size - 1 - i), symbol)) count++;
        }

        return count;
    }

    private boolean isPathOpen(Board board, Function<Integer, Cell> cellGetter, Symbol symbol) {
        for (int i = 0; i < board.getSize(); i++) {
            Cell cell = cellGetter.apply(i);
            if (cell.getState() == CellState.FILLED && cell.getPlayer().getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }
}
