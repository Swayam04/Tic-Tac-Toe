package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.game.Symbol;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.strategies.WinnerChecker;

import java.util.ArrayList;
import java.util.List;

public class MediumBotStrategy implements BotStrategy {
    private final BotPlayer botPlayer;
    private final WinnerChecker winnerChecker;
    private final EasyBotStrategy easyBotStrategy;

    public MediumBotStrategy(BotPlayer botPlayer, WinnerChecker winnerChecker, EasyBotStrategy easyBotStrategy) {
        this.botPlayer = botPlayer;
        this.winnerChecker = winnerChecker;
        this.easyBotStrategy = easyBotStrategy;
    }

    @Override
    public Move calculateNextMove(Board board) {
        List<Cell> blockingCells = new ArrayList<>();
        boolean isCentreEmpty = false;
        int mid = board.getSize() / 2;
        for(List<Cell> row : board.getBoard()) {
            for(Cell cell : row) {
                if(cell.getState() == CellState.EMPTY) {
                    if(isWinningMove(board, cell)) {
                        return new Move(cell, botPlayer);
                    } else if(isBlockingMove(board, cell)) {
                        blockingCells.add(cell);
                    } else if(cell.getRow() == mid && cell.getColumn() == mid) {
                        isCentreEmpty = true;
                    }
                }
            }
        }
        if(!blockingCells.isEmpty()) {
            return new Move(blockingCells.get(0), botPlayer);
        } else if(isCentreEmpty) {
            return new Move(board.getBoard().get(mid).get(mid), botPlayer);
        } else {
            return easyBotStrategy.calculateNextMove(board);
        }
    }

    private boolean isWinningMove(Board board, Cell cell) {
        return winnerChecker.checkWinner(board, cell, botPlayer.getSymbol(), true);
    }

    private boolean isBlockingMove(Board board, Cell cell) {
        Symbol opponentSymbol = botPlayer.getSymbol() == Symbol.X ? Symbol.O : Symbol.X;
        return winnerChecker.checkWinner(board, cell, opponentSymbol, true);
    }
}
