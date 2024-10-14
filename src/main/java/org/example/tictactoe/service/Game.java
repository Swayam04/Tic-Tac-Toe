package org.example.tictactoe.service;

import org.example.tictactoe.entities.board.Board;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.board.CellState;
import org.example.tictactoe.entities.game.GameState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.strategies.WinnerChecker;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private final List<Move> moves;
    private GameState state;
    private final WinnerChecker winnerChecker;

    public Game() {
        this.board = new Board();
        this.state = GameState.In_Progress;
        this.winnerChecker = new WinnerChecker();
        this.moves = new ArrayList<>();
    }

    public void printBoard() {
        board.displayBoard();
        System.out.println();
    }

    public boolean isValidMove(Move move) {
        int row = move.cell().getRow();
        int column = move.cell().getColumn();

        if (row < board.getSize() && column < board.getSize()) {
            return move.cell().getState() == CellState.EMPTY;
        }

        return  false;
    }

    public boolean checkWinner(Move move) {
        return winnerChecker.checkWinner(this.board, move.getCell(), move.getPlayer().getSymbol(),false);
    }

    public void makePlayerMove(Move move) {
        Cell cell = move.getCell();
        cell.setPlayer(move.getPlayer());
        cell.setState(CellState.FILLED);
        moves.add(move);
        if (checkWinner(move)) {
            state = GameState.Win;
        } else if (moves.size() == this.board.getSize() * this.board.getSize()) {
            state = GameState.Draw;
        }
    }

    public void makeBotMove(BotPlayer botPlayer) {
        Move move = botPlayer.getStrategy(winnerChecker).calculateNextMove(this.board);
        makePlayerMove(move);
    }


    public GameState getState() {
        return state;
    }

    public List<List<Cell>> getBoard() {
        return board.getBoard();
    }
}
