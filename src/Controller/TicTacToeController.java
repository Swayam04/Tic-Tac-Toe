package Controller;

import Views.GamePlayView;
import models.game.Game;
import models.game.GameState;
import models.game.Move;
import models.game.Symbol;
import models.players.BotPlayer;
import models.players.Difficulty;
import models.players.HumanPlayer;
import models.players.Player;

public class TicTacToeController {
    private Game game;
    private Player player1;
    private Player player2;
    private final GamePlayView gameView = new GamePlayView();
    private Player currentPlayer;


    public void startGame() {
        this.game = new Game();
        this.currentPlayer = player1;
        playGame();
    }

    private void playGame() {
        System.out.println(player1.getName() + " " + player2.getName());
        while(this.game.getState() == GameState.In_Progress) {
            this.game.printBoard();
            if (currentPlayer.getClass() == HumanPlayer.class) {
                gameView.askForMoves(currentPlayer.getName());
                int row = gameView.getRow() - 1;
                int column = gameView.getColumn() - 1;
                Move move = new Move(game.getBoard().get(row).get(column), currentPlayer);
                if (!game.isValidMove(move)) {
                    System.out.println("Please enter a valid move!");
                    continue;
                }
                game.makePlayerMove(move);
            } else {
                game.makeBotMove();
            }
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }

        this.game.printBoard();

        if(this.game.getState() == GameState.Win) {
            Player winningPlayer = currentPlayer == player1 ? player2 : player1;
            System.out.println(winningPlayer.getName() + " has won the game!");
        }
        if(this.game.getState() == GameState.Draw) {
            System.out.println("Game has ended in a draw!");
        }

    }

    public void createHumanPlayer(String name) {
        Symbol symbol = player1 == null ? Symbol.X : Symbol.O;
        Player player = new HumanPlayer(symbol, name);
        setPlayers(player);
    }

    public void createBotPlayer(int diff) {
        Symbol symbol = player1 == null ? Symbol.X : Symbol.O;
        Difficulty difficulty = diff == 1 ? Difficulty.EASY : (diff == 2 ?  Difficulty.MEDIUM : Difficulty.HARD);
        Player player = new BotPlayer(symbol, difficulty);
        setPlayers(player);
    }

    private void setPlayers(Player player) {
        if (this.player1 == null) {
            this.player1 = player;
        } else if (this.player2 == null) {
            this.player2 = player;
        }
    }
}
