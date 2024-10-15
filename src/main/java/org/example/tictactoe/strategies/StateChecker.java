package org.example.tictactoe.strategies;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.Symbol;

public class StateChecker {

    public static boolean checkWinner(Board board, Symbol symbol) {
        if(isRowWin(board, symbol)) {
            return true;
        } else if(isColumnWin(board, symbol)) {
            return true;
        } else return isDiagonalWin(board, symbol);
    }

    public static boolean checkDraw(Board board) {
        for(int i = 0; i < board.getSize(); i++) {
            for(int j = 0; j < board.getSize(); j++) {
                if(board.getBoard().get(i).get(j).getState() == CellState.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isRowWin(Board board, Symbol symbol) {
        for(int row = 0; row < board.getSize(); row++) {
            if(board.getBoard().get(row).stream()
                    .allMatch(cell -> cell.getState() == CellState.FILLED &&
                            cell.getPlayer().getSymbol() == symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColumnWin(Board board, Symbol symbol) {
        for(int col = 0; col < board.getSize(); col++) {
            int finalCol = col;
            if(board.getBoard().stream()
                    .allMatch(row -> row.get(finalCol).getState() == CellState.FILLED
                    && row.get(finalCol).getPlayer().getSymbol() == symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(Board board, Symbol symbol) {
        boolean leftDiagonalWin = true;
        boolean rightDiagonalWin = true;
        for(int i = 0; i < board.getSize(); i++) {
            if(board.getBoard().get(i).get(i).getState() != CellState.FILLED ||
                board.getBoard().get(i).get(i).getPlayer().getSymbol() != symbol) {
                leftDiagonalWin = false;
            }
            if(board.getBoard().get(i).get(board.getSize() - i - 1).getState() != CellState.FILLED
                || board.getBoard().get(i).get(board.getSize() - i - 1).getPlayer().getSymbol() != symbol) {
                rightDiagonalWin = false;
            }
        }
        return leftDiagonalWin || rightDiagonalWin;
    }

}
