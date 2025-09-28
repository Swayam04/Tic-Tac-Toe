# TicTacToe JavaFX Desktop Game with AI

## Description

This project implements a JavaFX desktop TicTacToe game with both Player vs Player (PvP) and Player vs Bot modes. The game features a modern graphical user interface and is designed with Object-Oriented Design (OOD) principles, utilizing design patterns such as Strategy and Factory.

## Features

- JavaFX desktop interface with intuitive 3x3 grid
- Two game modes:
    - Human vs Human (PvP)
    - Human vs Bot (Easy/Medium/Hard difficulty)
- Three bot difficulty levels:
    1. **Easy**: Random move selection
    2. **Medium**: Heuristic-based move selection
    3. **Hard**: Minimax algorithm with alpha-beta pruning
- Non-blocking bot move execution with background thread processing
- Real-time status updates and game state management
- Implemented using OOD principles and design patterns

### Bot Difficulty Levels

- **Easy**: The bot randomly selects an empty cell for its move.
- **Medium**: The bot uses a heuristic approach:
    1. Plays a winning move if available
    2. Blocks the player's winning move if necessary
    3. Plays in the center if available
    4. If none of the above, selects a random empty cell
- **Hard**: The bot uses the Minimax algorithm with alpha-beta pruning:
    - Utilizes a heuristic score function that prioritizes:
        - Winning moves with less depth
        - Losing moves with more depth
        - For tie scenarios, calculates number of available open lines for winning and prioritizes based on depth

## Installation

### Prerequisites

- Java 17 or higher
- JavaFX runtime (included with OpenJDK distributions or available separately)

### Development Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd Tic-Tac-Toe
   ```

2. Build and run the application:
   ```bash
   # Development run (recommended)
   mvn -q -DskipTests javafx:run
   
   # Or compile and package
   mvn clean package
   ```

### Running the Application

#### Development Mode
```bash
mvn -q -DskipTests javafx:run
```

#### Packaged JAR
```bash
# Build the JAR
mvn clean package

# Run with JavaFX on module path (Java 17+)
java --module-path /path/to/javafx/lib --add-modules javafx.controls -jar target/TicTacToe-1.0-SNAPSHOT.jar

# Note: Running the JAR requires JavaFX on the classpath unless using jpackage
```

#### Native Distribution (Recommended for End Users)
Create native installers using jpackage:

**macOS:**
```bash
jpackage --input target/libs --main-jar TicTacToe-1.0-SNAPSHOT.jar --main-class org.example.tictactoe.ui.MainApp --name "TicTacToe" --type dmg
```

**Windows:**
```bash
jpackage --input target/libs --main-jar TicTacToe-1.0-SNAPSHOT.jar --main-class org.example.tictactoe.ui.MainApp --name "TicTacToe" --type exe
```

**Linux:**
```bash
jpackage --input target/libs --main-jar TicTacToe-1.0-SNAPSHOT.jar --main-class org.example.tictactoe.ui.MainApp --name "TicTacToe" --type deb
```

## Usage

1. Launch the application using one of the methods above
2. The game window will open with:
   - Mode selection buttons (Human vs Human, Human vs Bot)
   - Bot difficulty selection (Easy/Medium/Hard) via dropdown
   - 3x3 game board grid
   - Status display and New Game button

3. Game Flow:
   - Click "Human vs Human" for two-player mode
   - Click "Human vs Bot" dropdown and select difficulty for single-player mode
   - Click on any empty cell to make a move
   - The game automatically handles turn switching and win/draw detection
   - Bot moves are calculated in the background with visual feedback
   - Click "New Game" to restart at any time

## JavaFX Runtime Notes

- For development: Use `mvn javafx:run` which handles JavaFX modules automatically
- For packaged JAR: JavaFX must be on the classpath or module path
- For distribution: Use `jpackage` to create native installers with bundled runtime
