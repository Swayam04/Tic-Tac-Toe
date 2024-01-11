package Controller;

import Views.GamePlayView;
import Views.StartView;

import java.util.Scanner;

public class GameController {

    public void  initiateGame() {
        Scanner scanner = new Scanner(System.in);
        TicTacToeController ticTacToeController = new TicTacToeController(scanner);
        StartView startView = new StartView(ticTacToeController, scanner);
        startView.displayStartView();
    }

}
