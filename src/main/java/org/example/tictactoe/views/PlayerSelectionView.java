package org.example.tictactoe.views;

import org.example.tictactoe.controllers.TicTacToeController;
import org.example.tictactoe.views.Enums.PlayerOption;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerSelectionView {
    private final Scanner scanner;
    private final TicTacToeController controller;

    public PlayerSelectionView(TicTacToeController controller, Scanner scanner) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void selectPlayer(int playerNumber) {
        selectionText(playerNumber);
        int player = scanner.nextInt();
        if (player == PlayerOption.Human_Player.getValue()) {
            getPlayerDetails();
        } else if (player == PlayerOption.Bot_Player.getValue()) {
            getBotDetails();
        } else {
            System.out.println("Invalid number. Please enter 1 for Human Player or 2 for Bot\n");
            selectPlayer(playerNumber);
        }
    }

    private void getPlayerDetails() {
        System.out.println();
        System.out.println("Enter name: ");
        String name = scanner.next();
        controller.createHumanPlayer(name);
    }

    private void getBotDetails() {
        System.out.println();
        System.out.println("Choose the Bot difficulty:\n1. Easy\n2. Medium\n3. Hard");
        try {
            int difficulty = scanner.nextInt();

            if (difficulty <= 3 && difficulty >= 1) {
                controller.createBotPlayer(difficulty);
            } else {
                System.out.println("Invalid number. Please enter 1, 2, or 3 based on your preference of difficulty");
                scanner.nextLine();
                getBotDetails();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please try again.");
            scanner.nextLine();
            getBotDetails();
        }
    }

    private void selectionText(int playerNumber) {
        System.out.println("Select Player Type for Player " + playerNumber + ":");
        System.out.println(PlayerOption.Human_Player.getValue() + ". Human");
        System.out.println(PlayerOption.Bot_Player.getValue() + ". Bot");
    }

}
