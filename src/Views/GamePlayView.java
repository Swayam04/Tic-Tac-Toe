package Views;

import java.util.Scanner;

public class GamePlayView {
    private final Scanner scanner = new Scanner(System.in);

    public void askForMoves(String name) {
        System.out.println();
        System.out.println(name + " to make move. Choose row and column from 1 to 3.");
    }

    public int getRow() {
        System.out.println("Row: ");
        return scanner.nextInt();
    }

    public int getColumn() {
        System.out.println("Column: ");
        return scanner.nextInt();
    }

}
