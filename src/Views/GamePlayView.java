package Views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GamePlayView {
    private final Scanner scanner;

    public GamePlayView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void askForMoves(String name) {
        System.out.println();
        System.out.println(name + " to make move. Choose row and column from 1 to 3.");
    }

    public int getRow() {
        System.out.println("Row: ");
        return getInput();
    }

    public int getColumn() {
        System.out.println("Column: ");
        return getInput();
    }

    private int getInput() {
        try {
            int value = scanner.nextInt();
            if (value <= 3 && value >= 1) {
                return value;
            } else {
                System.out.println("Invalid row/column number. Enter again: ");
                return getInput();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input. Enter again: ");
            scanner.nextLine();
            return getInput();
        }
    }

}
