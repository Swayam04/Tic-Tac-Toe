package org.example.tictactoe.ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.example.tictactoe.entities.board.Cell;
import org.example.tictactoe.entities.game.GameState;
import org.example.tictactoe.entities.game.Move;
import org.example.tictactoe.entities.game.Symbol;
import org.example.tictactoe.entities.players.*;
import org.example.tictactoe.service.Game;

public class TicTacToeFX {

    private final BorderPane root = new BorderPane();
    private final GridPane grid = new GridPane();
    private final Label status = new Label("Welcome");
    private final Button restart = new Button("Restart");
    private final ComboBox<String> modeSelect = new ComboBox<>();

    private Game game;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToeFX() {
        buildUI();
        wireEvents();
    }

    public Pane getRoot() {
        return root;
    }

    public void startNewGame() {
        String mode = modeSelect.getValue();
        if (mode == null) mode = "Human vs Bot (Medium)";

        this.game = new Game();
        this.player1 = new HumanPlayer(Symbol.X, "Player 1");
        this.player2 = switch (mode) {
            case "Human vs Bot (Easy)" -> new BotPlayer(Symbol.O, Difficulty.EASY);
            case "Human vs Bot (Hard)" -> new BotPlayer(Symbol.O, Difficulty.HARD);
            case "Human vs Human" -> new HumanPlayer(Symbol.O, "Player 2");
            default -> new BotPlayer(Symbol.O, Difficulty.MEDIUM);
        };
        this.currentPlayer = player1;

        buildBoardButtons();
        updateStatus();
    }

    // -------------------- UI construction --------------------

    private void buildUI() {
        // Top bar
        var topLeft = new HBox(10);
        modeSelect.getItems().addAll(
                "Human vs Bot (Easy)",
                "Human vs Bot (Medium)",
                "Human vs Bot (Hard)",
                "Human vs Human"
        );
        modeSelect.setValue("Human vs Bot (Medium)");
        topLeft.getChildren().addAll(new Label("Mode:"), modeSelect, restart);
        topLeft.setAlignment(Pos.CENTER_LEFT);

        var top = new BorderPane();
        var title = new Label("Tic-Tac-Toe");
        title.setFont(Font.font(18));
        top.setLeft(topLeft);
        top.setRight(title);
        BorderPane.setMargin(topLeft, new Insets(10));
        BorderPane.setMargin(title, new Insets(10));

        // Center: Board grid
        grid.setHgap(6);
        grid.setVgap(6);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);

        // Bottom: status
        var bottom = new BorderPane();
        status.setPadding(new Insets(8));
        bottom.setCenter(status);

        root.setTop(top);
        root.setCenter(grid);
        root.setBottom(bottom);
        root.setPadding(new Insets(10));
    }

    private void wireEvents() {
        restart.setOnAction(e -> startNewGame());
        modeSelect.setOnAction(e -> startNewGame());
    }

    private void buildBoardButtons() {
        grid.getChildren().clear();
        int size = game.getBoardSize(); // get size through Game class
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Button cellBtn = new Button(" ");
                cellBtn.setPrefSize(90, 90);
                cellBtn.setFont(Font.font(24));
                final int row = r, col = c;
                cellBtn.setOnAction(e -> onCellClick(row, col));
                grid.add(cellBtn, c, r);
            }
        }
        redrawBoard();
    }

    // -------------------- Game interactions --------------------

    private void onCellClick(int row, int col) {
        if (isGameOver()) return;
        if (!(currentPlayer instanceof HumanPlayer)) return; // ignore clicks during bot turn

        try {
            Cell cell = game.getBoard().get(row).get(col);
            Move move = new Move(cell, currentPlayer);

            if (!game.isValidMove(move)) {
                status.setText("Invalid move. Try again.");
                return;
            }

            game.makePlayerMove(move);
            redrawBoard();

            if (isGameOver()) {
                updateStatus();
                return;
            }

            togglePlayer();

            // If it's bot's turn, let the bot play (off UI thread for hard strategy)
            if (currentPlayer instanceof BotPlayer bot) {
                status.setText("Bot is thinking...");
                runBotTurn(bot);
            } else {
                updateStatus();
            }

        } catch (IndexOutOfBoundsException ex) {
            status.setText("Out of bounds. Try again.");
        }
    }

    private void runBotTurn(BotPlayer bot) {
        new Thread(() -> {
            try {
                game.makeBotMove(bot);
            } finally {
                Platform.runLater(() -> {
                    redrawBoard();

                    if (!isGameOver()) {
                        togglePlayer(); // back to human
                        updateStatus();
                    } else {
                        updateStatus();
                    }
                });
            }
        }, "bot-move-thread").start();
    }

    private void togglePlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    private boolean isGameOver() {
        GameState s = game.getState();
        return s == GameState.Draw || s == GameState.Win;
    }

    private void redrawBoard() {
        int size = game.getBoardSize();
        for (var node : grid.getChildren()) {
            if (!(node instanceof Button btn)) continue;
            Integer col = GridPane.getColumnIndex(btn);
            Integer row = GridPane.getRowIndex(btn);
            if (col == null || row == null) continue;

            Cell cell = game.getBoard().get(row).get(col);
            String text = cell.getPlayer() != null ? cell.getPlayer().getSymbol().name() : " ";
            btn.setText(text);
            btn.setDisable(cell.getPlayer() != null || isGameOver());
        }
    }

    private void updateStatus() {
        if (game.getState() == GameState.Win) {
            // Winner is the one who just played (opposite of current)
            Player winner = currentPlayer == player1 ? player2 : player1;
            status.setText(winner.getName() + " wins!");
        } else if (game.getState() == GameState.Draw) {
            status.setText("Game ended in a draw.");
        } else {
            status.setText("Turn: " + currentPlayer.getName());
        }
    }
}