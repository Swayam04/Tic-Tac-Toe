package org.example.tictactoe.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        var view = new TicTacToeFX();
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(new Scene(view.getRoot()));
        stage.setResizable(false);
        stage.show();
        view.startNewGame(); // default mode from the UI control
    }

    public static void main(String[] args) {
        launch(args);
    }
}