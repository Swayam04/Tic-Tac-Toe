# Tic-Tac-Toe (JavaFX Desktop UI)

This project is a Java Tic-Tac-Toe with:
- Human vs Human
- Human vs Bot (Easy / Medium / Hard)

The desktop UI is built with JavaFX and reuses the existing game logic.

## Run during development

Requirements:
- JDK 21+
- Maven 3.9+

Install dependencies and run the UI:

```bash
mvn -q -DskipTests javafx:run
```

## Build a JAR

```bash
mvn clean package
```

Note: Running the resulting JAR directly requires JavaFX on the module/classpath. For a self-contained app, use `jpackage` (below) to bundle a runtime.

## Package for desktop (jpackage)

Requires a JDK that includes `jpackage` (e.g., Oracle/OpenJDK 14+). Example commands:

```bash
# macOS DMG
jpackage \
  --name TicTacToe \
  --input target \
  --main-jar TicTacToe-1.0-SNAPSHOT.jar \
  --main-class org.example.tictactoe.ui.MainApp \
  --type dmg

# Windows EXE (run in PowerShell or CMD)
jpackage \
  --name TicTacToe \
  --input target \
  --main-jar TicTacToe-1.0-SNAPSHOT.jar \
  --main-class org.example.tictactoe.ui.MainApp \
  --type exe

# Linux DEB
jpackage \
  --name TicTacToe \
  --input target \
  --main-jar TicTacToe-1.0-SNAPSHOT.jar \
  --main-class org.example.tictactoe.ui.MainApp \
  --type deb
```

You can add icons with `--icon` pointing to a `.icns` (macOS), `.ico` (Windows) or `.png` (Linux) file.

## Console app

The original console app still exists (org.example.tictactoe.TicTacToe) but the default JAR runs the UI. You can run the console main with your IDE or by changing the `maven-jar-plugin` `mainClass`.
