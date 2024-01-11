package models.game;

import models.board.Board;
import models.board.CellState;
import models.players.Player;
import models.strategies.WinnerChecker;

import java.util.List;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Board board;
    private List<Move> moves;
    private GameState state;
    private final WinnerChecker winnerChecker;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        this.state = GameState.In_Progress;
        this.winnerChecker = new WinnerChecker();
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

    public boolean checkWinner(Board board, Move move) {
        return winnerChecker.checkWinner(board, move);
    }

    public void makeMove() {

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameState getState() {
        return state;
    }
}
