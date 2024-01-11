package models.game;

import models.board.Board;
import models.board.Cell;
import models.board.CellState;
import models.players.Player;
import models.strategies.WinnerChecker;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private List<Move> moves;
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
        return winnerChecker.checkWinner(this.board, move);
    }

    public void makePlayerMove(Move move) {
        Cell cell = move.getCell();
        cell.setPlayer(move.getPlayer());
        cell.setState(CellState.FILLED);
        moves.add(move);
        if (checkWinner(move)) {
            state = GameState.Win;
        }
        if (moves.size() == this.board.getSize() * this.board.getSize()) {
            state = GameState.Draw;
        }
    }

    public void makeBotMove() {

    }


    public GameState getState() {
        return state;
    }

    public List<List<Cell>> getBoard() {
        return board.getBoard();
    }
}
