package org.example.tictactoe.strategies.botstrategies;

import org.example.tictactoe.controllers.MoveController;
import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.game.Symbol;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.entities.players.HumanPlayer;
import org.example.tictactoe.strategies.StateChecker;

import java.util.ArrayList;
import java.util.List;

public class MediumBotStrategy implements BotStrategy {
    private final BotPlayer botPlayer;
    private final EasyBotStrategy easyBotStrategy;

    public MediumBotStrategy(BotPlayer botPlayer, EasyBotStrategy easyBotStrategy) {
        this.botPlayer = botPlayer;
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
        Move tempMove = new Move(cell, botPlayer);
        MoveController.makeMove(tempMove);
        boolean isWin = StateChecker.checkWinner(board, botPlayer.getSymbol());
        MoveController.undoMove(tempMove);
        return isWin;
    }

    private boolean isBlockingMove(Board board, Cell cell) {
        Symbol opponentSymbol = botPlayer.getSymbol() == Symbol.X ? Symbol.O : Symbol.X;
        Move tempMove = new Move(cell, new HumanPlayer(opponentSymbol, "TempPlayer"));
        MoveController.makeMove(tempMove);
        boolean isBlocking = StateChecker.checkWinner(board, opponentSymbol);
        MoveController.undoMove(tempMove);
        return isBlocking;
    }
}
