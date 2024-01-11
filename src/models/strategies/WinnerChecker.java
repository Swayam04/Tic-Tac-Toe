package models.strategies;

import models.board.Board;
import models.game.Move;
import models.game.Symbol;

import java.util.HashMap;
import java.util.Map;

public class WinnerChecker {
    private final Map<Integer, Map<Symbol, Integer>> rowCounts = new HashMap<>();
    private final Map<Integer, Map<Symbol, Integer>> columnCounts = new HashMap<>();
    private final Map<Diagonal, Map<Symbol, Integer>> diagonalCounts = new HashMap<>();

    public boolean checkWinner(Board board, Move move) {
        int row = move.cell().getRow();
        int column = move.cell().getColumn();
        Symbol symbol = move.player().getSymbol();

        updateCounts(rowCounts, row, symbol);
        updateCounts(columnCounts, column, symbol);

        if (row == column || row + column == board.getSize() - 1) {
            updateDiagonalCounts(diagonalCounts, row, column, symbol);
        }

        return checkCounts(rowCounts.get(row), symbol, board.getSize()) ||
                checkCounts(columnCounts.get(column), symbol, board.getSize()) ||
                checkCounts(diagonalCounts.get(Diagonal.LEFT), symbol, board.getSize()) ||
                checkCounts(diagonalCounts.get(Diagonal.RIGHT), symbol, board.getSize());
    }

    private void updateCounts(Map<Integer, Map<Symbol, Integer>> counts, int key, Symbol symbol) {
        counts.putIfAbsent(key, new HashMap<>());
        Map<Symbol, Integer> map = counts.get(key);
        map.putIfAbsent(symbol, 0);
        map.put(symbol, map.get(symbol) + 1);
    }

    private void updateDiagonalCounts(Map<Diagonal, Map<Symbol, Integer>> diagonalCounts, int row, int col, Symbol symbol) {
        diagonalCounts.putIfAbsent(Diagonal.LEFT, new HashMap<>());
        diagonalCounts.putIfAbsent(Diagonal.RIGHT, new HashMap<>());

        Map<Symbol, Integer> diagonalMap = diagonalCounts.get(row == col ? Diagonal.LEFT : Diagonal.RIGHT);
        diagonalMap.putIfAbsent(symbol, 0);
        diagonalMap.put(symbol, diagonalMap.get(symbol) + 1);
    }

    private boolean checkCounts(Map<Symbol, Integer> countMap, Symbol symbol, int size) {
        return countMap != null && countMap.getOrDefault(symbol, 0) == size;
    }

}
