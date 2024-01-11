import Controller.GameController;
import Views.StartView;

public class TicTacToe {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.initiateGame();
    }
}
