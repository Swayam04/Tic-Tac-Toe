package Views;

import Controller.TicTacToeController;

import java.util.Scanner;

public class StartView {
    private final Scanner scanner;
    private final PlayerSelectionView setupGame;
    private final TicTacToeController controller;

    public  StartView() {
        this.scanner = new Scanner(System.in);
        this.setupGame = new PlayerSelectionView();
        this.controller = new TicTacToeController();
    }

    public void displayStartView() {
        System.out.println("Tic Tac Toe\n");
        System.out.println("Please choose an option");
        System.out.println("1. Start Game");
        System.out.println("2. Exit");

        int option = scanner.nextInt();

        if (option == 2) {
            System.out.println("Thank you!");
            System.exit(0);
        } else {
            System.out.println();
            setupGame.selectPlayer(1);
            System.out.println();
            setupGame.selectPlayer(2);
            controller.startGame();
        }
    }
}
