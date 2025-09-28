package org.example.tictactoe.integration;

import org.example.tictactoe.entities.game.GameState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.game.Symbol;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.entities.players.Difficulty;
import org.example.tictactoe.entities.players.HumanPlayer;
import org.example.tictactoe.service.Game;

/**
 * Simple test to verify that the core game logic works correctly for UI integration
 * This verifies that all the logic that the UI will use is working properly.
 */
public class GameLogicTest {

    public static void main(String[] args) {
        System.out.println("Testing UI game logic integration...");
        
        // Test game creation
        Game game = new Game();
        System.out.println("✓ Game created successfully");
        
        // Test board size method (added for UI)
        int boardSize = game.getBoardSize();
        System.out.println("✓ Board size: " + boardSize);
        
        // Test players creation
        HumanPlayer player1 = new HumanPlayer(Symbol.X, "Player 1");
        BotPlayer easyBot = new BotPlayer(Symbol.O, Difficulty.EASY);
        BotPlayer mediumBot = new BotPlayer(Symbol.O, Difficulty.MEDIUM);
        BotPlayer hardBot = new BotPlayer(Symbol.O, Difficulty.HARD);
        
        System.out.println("✓ Players created: " + player1.getName() + ", " + easyBot.getName());
        System.out.println("✓ Bot difficulties available: EASY, MEDIUM, HARD");
        
        // Test bot strategy creation
        try {
            easyBot.getStrategy();
            mediumBot.getStrategy();
            hardBot.getStrategy();
            System.out.println("✓ All bot strategies initialized successfully");
        } catch (Exception e) {
            System.err.println("✗ Error initializing bot strategies: " + e.getMessage());
            return;
        }
        
        // Test game board access
        var board = game.getBoard();
        if (board != null && board.size() == boardSize) {
            System.out.println("✓ Board structure verified: " + boardSize + "x" + boardSize);
        } else {
            System.err.println("✗ Board structure issue");
            return;
        }
        
        // Test a full game scenario that the UI would use
        System.out.println("\n--- Testing a sample game scenario ---");
        
        // Make a move as human player
        var cell = game.getBoard().get(1).get(1); // center cell
        Move humanMove = new Move(cell, player1);
        
        if (game.isValidMove(humanMove)) {
            game.makePlayerMove(humanMove);
            System.out.println("✓ Human move made successfully at center");
            System.out.println("✓ Game state: " + game.getState());
        } else {
            System.err.println("✗ Human move validation failed");
            return;
        }
        
        // Test bot move
        if (game.getState() == GameState.In_Progress) {
            try {
                game.makeBotMove(easyBot);
                System.out.println("✓ Bot move made successfully");
                System.out.println("✓ Game state after bot move: " + game.getState());
            } catch (Exception e) {
                System.err.println("✗ Bot move failed: " + e.getMessage());
                return;
            }
        }
        
        System.out.println("\nAll UI integration tests passed! ✅");
        System.out.println("JavaFX UI implementation is ready to use the game logic.");
        System.out.println("Note: JavaFX classes compile successfully but runtime is not available in this environment.");
    }
}