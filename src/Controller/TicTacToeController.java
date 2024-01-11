package Controller;

import models.game.Game;
import models.game.Symbol;
import models.players.BotPlayer;
import models.players.Difficulty;
import models.players.HumanPlayer;
import models.players.Player;

public class TicTacToeController {
    private Game game;
    private Player player1;
    private Player player2;


    public void startGame() {
        this.game = new Game(player1, player2);
        game.printBoard();
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
        if (player1 == null) {
            player2 = player;
        } else {
            player1 = player;
        }
    }

}
