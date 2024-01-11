package models.game;

import models.board.Cell;
import models.players.Player;

public record Move(Cell cell, Player player) {
}
