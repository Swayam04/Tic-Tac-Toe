package models.board;

import models.players.Player;

public class Cell {
    private final int row;
    private final int column;
    private Player player;
    private CellState state;

    public Cell(int row, int col) {
        this.row = row;
        this.column = col;
        this.state = CellState.EMPTY;
    }

    public void displayCell() {
        if (player == null) {
            System.out.print("|---|");
        } else {
            System.out.print("| " + player.getSymbol() + " |");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
