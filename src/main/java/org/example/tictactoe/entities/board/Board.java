package org.example.tictactoe.entities.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int size;
    private final List<List<Cell>> board;

    public Board() {
        this.board = new ArrayList<>();
        this.size = 3;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < this.size; i++) {
            board.add(new ArrayList<>());

            for (int j = 0; j < this.size; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void displayBoard() {
        for (List<Cell> row : board) {
            for (Cell cell : row) {
                cell.displayCell();
            }
            System.out.print("\n");
        }
    }


    public List<List<Cell>> getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }
}
