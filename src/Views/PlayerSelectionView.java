package Views;

import Controller.TicTacToeController;
import Views.Enums.PlayerOption;
import models.players.HumanPlayer;

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
            System.out.println("Invalid number. Please enter 1 for Player or 2 for Bot\n");
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
        int difficulty = scanner.nextInt();
        controller.createBotPlayer(difficulty);
    }

    private void selectionText(int playerNumber) {
        System.out.println("Select Player Type for Player " + playerNumber + ":");
        System.out.println(PlayerOption.Human_Player.getValue() + ". Human");
        System.out.println(PlayerOption.Bot_Player.getValue() + ". Bot");
    }

}
