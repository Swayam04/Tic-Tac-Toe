package org.example.tictactoe.service;

import org.example.tictactoe.controllers.MoveController;
import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.GameState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.strategies.StateChecker;

import java.util.List;

public class Game {
    private final Board board;
    private GameState state;

    public Game() {
        this.board = new Board();
        this.state = GameState.In_Progress;
    }

    public boolean isValidMove(Move move) {
        return move.getCell().getState() == CellState.EMPTY;
    }

    public boolean checkWinner(Move move) {
        return StateChecker.checkWinner(board, move.getPlayer().getSymbol());
    }

    public boolean checkDraw() {
        return StateChecker.checkDraw(board);
    }

    public void makePlayerMove(Move move) {
        MoveController.makeMove(move);
        if (checkWinner(move)) {
            state = GameState.Win;
        } else if (checkDraw()) {
            state = GameState.Draw;
        }
    }

    public void makeBotMove(BotPlayer botPlayer) {
        Move move = botPlayer.getStrategy().calculateNextMove(this.board);
        makePlayerMove(move);
    }


    public GameState getState() {
        return state;
    }

    public List<List<Cell>> getBoard() {
        return board.getBoard();
    }

    public Board getBoardObject() {
        return board;
    }

    public int getBoardSize() {
        return board.getSize();
    }
}
