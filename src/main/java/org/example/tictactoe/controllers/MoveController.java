package org.example.tictactoe.controllers;

import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.Move;

public class MoveController {

    public static void makeMove(Move move) {
        Cell cell = move.getCell();
        cell.setPlayer(move.getPlayer());
        cell.setState(CellState.FILLED);
    }

    public static void undoMove(Move move) {
        Cell cell = move.getCell();
        cell.setPlayer(null);
        cell.setState(CellState.EMPTY);
    }

}
