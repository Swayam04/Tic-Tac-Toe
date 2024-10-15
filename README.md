# TicTacToe Console Game with AI

## Description

This project implements a console-based TicTacToe game with both Player vs Player (PvP) and Player vs Bot modes. The game is designed with Object-Oriented Design (OOD) principles and utilizes design patterns such as Strategy and Factory.

## Features

- Console-based interface
- Two game modes:
    - Player vs Player (PvP)
    - Player vs Bot
- Three bot difficulty levels:
    1. Easy: Random move selection
    2. Medium: Heuristic-based move selection
    3. Hard: Minimax algorithm with alpha-beta pruning
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

- Java 21 or higher

### Steps

1. Download the JAR file:
   [Download TicTacToe.jar](https://drive.google.com/file/d/1iwFzDdwN-qED_9ITa1n59PgG3rmyX4pJ/view?usp=drive_link)

2. Open a terminal or command prompt

3. Navigate to the directory containing the downloaded JAR file

4. Run the game using the following command:

   On Windows/macOS/Linux:
   ```
   java -jar TicTacToe-1.0-SNAPSHOT.jar
   ```


## Usage

1. Launch the game using the command provided in the Installation section
2. Follow the on-screen prompts to:
    - Select game mode (PvP or Player vs Bot)
    - Choose bot difficulty (if applicable)
    - Make moves by entering the corresponding cell number
