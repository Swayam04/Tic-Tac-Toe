package Views;

import Controller.TicTacToeController;

import java.util.Scanner;

public class PlayerSelectionView {
    private final Scanner scanner;
    private TicTacToeController controller;

    public PlayerSelectionView() {
        this.scanner = new Scanner(System.in);
        controller = new TicTacToeController();
    }

    public void selectPlayer(int playerNumber) {
        selectionText(playerNumber);
        int player = scanner.nextInt();
        if (player == 1) {
            getPlayerDetails();
        } else {
            getBotDetails();
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
        System.out.println("1. Human\n2. Bot");
    }

}
