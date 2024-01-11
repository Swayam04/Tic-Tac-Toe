package Controller;

import Views.GamePlayView;
import Views.StartView;

import java.util.Scanner;

public class GameController {

    public void  initiateGame() {
        TicTacToeController ticTacToeController = new TicTacToeController();
        Scanner scanner = new Scanner(System.in);
        StartView startView = new StartView(ticTacToeController, scanner);
        startView.displayStartView();
    }

}
