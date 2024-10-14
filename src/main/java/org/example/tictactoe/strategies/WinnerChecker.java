package org.example.tictactoe.strategies;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.game.Symbol;

import java.util.HashMap;
import java.util.Map;

public class WinnerChecker {
    private final Map<Integer, Map<Symbol, Integer>> rowCounts = new HashMap<>();
    private final Map<Integer, Map<Symbol, Integer>> columnCounts = new HashMap<>();
    private final Map<Diagonal, Map<Symbol, Integer>> diagonalCounts = new HashMap<>();

    public boolean checkWinner(Board board, Cell cell, Symbol symbol, boolean isTemporary) {
        int row = cell.getRow();
        int column = cell.getColumn();

        adjustCount(rowCounts, row, symbol, 1);
        adjustCount(columnCounts, column, symbol, 1);
        if (row == column || row + column == board.getSize() - 1) {
            adjustDiagonalCounts(diagonalCounts, row, column, symbol, 1);
        }

        boolean isWin = checkCounts(rowCounts.get(row), symbol, board.getSize()) ||
                checkCounts(columnCounts.get(column), symbol, board.getSize()) ||
                checkCounts(diagonalCounts.get(Diagonal.LEFT), symbol, board.getSize()) ||
                checkCounts(diagonalCounts.get(Diagonal.RIGHT), symbol, board.getSize());

        if (isTemporary) {
            adjustCount(rowCounts, row, symbol, -1);
            adjustCount(columnCounts, column, symbol, -1);
            if (row == column || row + column == board.getSize() - 1) {
                adjustDiagonalCounts(diagonalCounts, row, column, symbol, -1);
            }
        }
        return isWin;
    }

    private boolean checkCounts(Map<Symbol, Integer> countMap, Symbol symbol, int size) {
        return countMap != null && countMap.getOrDefault(symbol, 0) == size;
    }

    private void adjustCount(Map<Integer, Map<Symbol, Integer>> counts, int key, Symbol symbol, int delta) {
        counts.putIfAbsent(key, new HashMap<>());
        Map<Symbol, Integer> map = counts.get(key);
        map.putIfAbsent(symbol, 0);
        map.put(symbol, map.getOrDefault(symbol, 0) + delta);
    }

    private void adjustDiagonalCounts(Map<Diagonal, Map<Symbol, Integer>> diagonalCounts, int row, int col, Symbol symbol, int delta) {
        diagonalCounts.putIfAbsent(Diagonal.LEFT, new HashMap<>());
        diagonalCounts.putIfAbsent(Diagonal.RIGHT, new HashMap<>());
        Map<Symbol, Integer> diagonalMap = diagonalCounts.get(row == col ? Diagonal.LEFT : Diagonal.RIGHT);
        diagonalMap.putIfAbsent(symbol, 0);
        diagonalMap.put(symbol, diagonalMap.get(symbol) + delta);
    }

}
