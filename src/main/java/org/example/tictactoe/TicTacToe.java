package org.example.tictactoe;

import org.example.tictactoe.controllers.GameController;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.initiateGame();
    }
}
