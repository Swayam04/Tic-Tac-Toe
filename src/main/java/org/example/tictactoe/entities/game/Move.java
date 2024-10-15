package org.example.tictactoe.entities.game;

import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.players.Player;

public class Move {
    private final Cell cell;
    private final Player player;

    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }

    public Cell getCell() {
        return this.cell;
    }

    public Player getPlayer() {
        return this.player;
    }
}
