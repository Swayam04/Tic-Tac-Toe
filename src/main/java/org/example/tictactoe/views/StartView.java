package org.example.tictactoe.views;

import org.example.tictactoe.controllers.TicTacToeController;
import org.example.tictactoe.views.Enums.StartMenuOption;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StartView {
    private final Scanner scanner;
    private final PlayerSelectionView setupGame;
    private final TicTacToeController controller;

    public  StartView(TicTacToeController controller, Scanner scanner) {
        this.scanner = scanner;
        this.controller = controller;
        this.setupGame = new PlayerSelectionView(controller, scanner);
    }

    public void displayStartView() {
        System.out.println("Tic Tac Toe\n");
        System.out.println("Please choose an option");
        System.out.println(StartMenuOption.START_GAME.getValue() +". Start Game");
        System.out.println(StartMenuOption.EXIT.getValue() + ". Exit");

        int option = scanner.nextInt();

        try {
            if (option == StartMenuOption.EXIT.getValue()) {
                System.out.println("Thank you!");
                System.exit(0);
            } else if(option == StartMenuOption.START_GAME.getValue()) {
                startPlayerSelection();
            } else {
                System.out.println("Invalid option. Please enter either 1 or 0\n");
                scanner.nextLine();
                displayStartView();
            }
        } catch(InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number\n");
            scanner.nextLine();
            displayStartView();
        }
    }

    private void startPlayerSelection() {
        System.out.println();
        try {
            setupGame.selectPlayer(1);
            System.out.println();
            setupGame.selectPlayer(2);
            controller.startGame();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input during player selection. Please try again.");
            scanner.nextLine();
            startPlayerSelection();
        }
    }
}
