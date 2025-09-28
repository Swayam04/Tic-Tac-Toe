package org.example.tictactoe.ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.concurrent.Task;

import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.game.GameState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.game.Symbol;
import org.example.tictactoe.entities.players.BotPlayer;
import org.example.tictactoe.entities.players.Difficulty;
import org.example.tictactoe.entities.players.HumanPlayer;
import org.example.tictactoe.entities.players.Player;
import org.example.tictactoe.service.Game;

import java.util.List;

public class TicTacToeFX {
    private Game game;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Button[][] boardButtons;
    private Label statusLabel;
    private VBox root;
    private GridPane boardGrid;
    private boolean gameInProgress = false;

    public TicTacToeFX() {
        initializeUI();
    }

    private void initializeUI() {
        root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Title
        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setFont(Font.font("Arial", 28));
        titleLabel.setStyle("-fx-font-weight: bold;");

        // Mode selection
        HBox modeBox = createModeSelection();

        // Game board
        boardGrid = createGameBoard();

        // Status label
        statusLabel = new Label("Select game mode to start");
        statusLabel.setFont(Font.font("Arial", 16));
        statusLabel.setStyle("-fx-text-fill: #333;");

        // Restart button
        Button restartButton = new Button("New Game");
        restartButton.setFont(Font.font("Arial", 14));
        restartButton.setOnAction(e -> restartGame());

        root.getChildren().addAll(titleLabel, modeBox, boardGrid, statusLabel, restartButton);
    }

    private HBox createModeSelection() {
        HBox modeBox = new HBox(10);
        modeBox.setAlignment(Pos.CENTER);

        Label modeLabel = new Label("Game Mode:");
        modeLabel.setFont(Font.font("Arial", 14));

        Button pvpButton = new Button("Human vs Human");
        pvpButton.setOnAction(e -> startGame(false, null));

        MenuButton botButton = new MenuButton("Human vs Bot");
        
        MenuItem easyBot = new MenuItem("Easy Bot");
        easyBot.setOnAction(e -> startGame(true, Difficulty.EASY));
        
        MenuItem mediumBot = new MenuItem("Medium Bot");
        mediumBot.setOnAction(e -> startGame(true, Difficulty.MEDIUM));
        
        MenuItem hardBot = new MenuItem("Hard Bot");
        hardBot.setOnAction(e -> startGame(true, Difficulty.HARD));

        botButton.getItems().addAll(easyBot, mediumBot, hardBot);

        modeBox.getChildren().addAll(modeLabel, pvpButton, botButton);
        return modeBox;
    }

    private GridPane createGameBoard() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10;");

        boardButtons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button("");
                button.setPrefSize(120, 120);
                button.setFont(Font.font("Arial", 36));
                button.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 2;");
                button.setDisable(true);
                
                final int row = i, col = j;
                button.setOnAction(e -> handleCellClick(row, col));
                
                boardButtons[i][j] = button;
                grid.add(button, j, i);
            }
        }
        return grid;
    }

    private void startGame(boolean vsBot, Difficulty botDifficulty) {
        game = new Game();
        
        // Create players
        player1 = new HumanPlayer(Symbol.X, "Player 1");
        if (vsBot) {
            player2 = new BotPlayer(Symbol.O, botDifficulty);
        } else {
            player2 = new HumanPlayer(Symbol.O, "Player 2");
        }
        
        currentPlayer = player1;
        gameInProgress = true;
        
        // Reset and enable board
        resetBoard();
        enableBoard(true);
        
        updateStatus(currentPlayer.getName() + "'s turn");
    }

    private void restartGame() {
        if (game != null) {
            gameInProgress = false;
            enableBoard(false);
            resetBoard();
            updateStatus("Select game mode to start");
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j].setText("");
                boardButtons[i][j].setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 2;");
            }
        }
    }

    private void enableBoard(boolean enable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j].setDisable(!enable);
            }
        }
    }

    private void handleCellClick(int row, int col) {
        if (!gameInProgress || currentPlayer instanceof BotPlayer) {
            return;
        }

        // Get the cell from the game board
        List<List<Cell>> gameBoard = game.getBoard();
        Cell cell = gameBoard.get(row).get(col);
        Move move = new Move(cell, currentPlayer);

        if (!game.isValidMove(move)) {
            updateStatus("Invalid move! Cell is already occupied.");
            return;
        }

        // Make the move
        makeMove(move, row, col);
    }

    private void makeMove(Move move, int row, int col) {
        game.makePlayerMove(move);
        
        // Update UI button
        boardButtons[row][col].setText(move.getPlayer().getSymbol().toString());
        boardButtons[row][col].setStyle("-fx-background-color: #e8f4fd; -fx-border-color: #ccc; -fx-border-width: 2;");
        
        // Check game state
        if (game.getState() == GameState.Win) {
            gameInProgress = false;
            enableBoard(false);
            updateStatus(currentPlayer.getName() + " wins!");
            highlightWinner();
            return;
        } else if (game.getState() == GameState.Draw) {
            gameInProgress = false;
            enableBoard(false);
            updateStatus("Game ended in a draw!");
            return;
        }

        // Switch players
        currentPlayer = (currentPlayer == player1) ? player2 : player1;

        // If next player is bot, make bot move
        if (currentPlayer instanceof BotPlayer) {
            updateStatus("Bot is thinking...");
            enableBoard(false);
            
            Task<Move> botMoveTask = new Task<Move>() {
                @Override
                protected Move call() throws Exception {
                    // Add a small delay to make the bot move visible
                    Thread.sleep(1000);
                    BotPlayer botPlayer = (BotPlayer) currentPlayer;
                    return botPlayer.getStrategy().calculateNextMove(game.getBoardObject());
                }
            };

            botMoveTask.setOnSucceeded(e -> {
                Move botMove = botMoveTask.getValue();
                if (botMove != null) {
                    int botRow = botMove.getCell().getRow();
                    int botCol = botMove.getCell().getColumn();
                    makeMove(botMove, botRow, botCol);
                } else {
                    updateStatus("Bot couldn't make a move!");
                }
                
                if (gameInProgress && player1 instanceof HumanPlayer) {
                    enableBoard(true);
                }
            });

            botMoveTask.setOnFailed(e -> {
                updateStatus("Bot move failed!");
                enableBoard(true);
            });

            Thread botThread = new Thread(botMoveTask);
            botThread.setDaemon(true);
            botThread.start();
        } else {
            updateStatus(currentPlayer.getName() + "'s turn");
        }
    }

    private void highlightWinner() {
        // Simple highlighting - could be enhanced to show winning line
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!boardButtons[i][j].getText().isEmpty()) {
                    boardButtons[i][j].setStyle("-fx-background-color: #c8e6c9; -fx-border-color: #4caf50; -fx-border-width: 2;");
                }
            }
        }
    }

    private void updateStatus(String message) {
        Platform.runLater(() -> statusLabel.setText(message));
    }

    public Parent getRoot() {
        return root;
    }
}