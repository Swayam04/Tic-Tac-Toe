package org.example.tictactoe.entities.game;

import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.players.Player;

public record Move(Cell cell, Player player) {
    public Cell getCell() {
        return this.cell;
    }

    public Player getPlayer() {
        return this.player;
    }
}
