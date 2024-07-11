# Tic-tac-toe game

A simple tic-tac-toe game implemented in Java.

## Features

Three gameplay options:

1. **Basic**: classic tic-tac-toe for two players.
2. **Advanced**: tic-tac-toe for two players with additional features.
3. **CPU**: play against the computer with an option for both single and two-player modes.

## Getting Started

### Installation

1. Clone this repository to your local machine
   ```bash
   git clone https://github.com/DanielVegetto/tic-tac-toe.git
   ```
2. Navigate to the source directory inside the project directory
   ```bash
   cd tic-tac-toe/src
   ```
3. Compile the Java files
   ```bash
   javac *.java
   ```
4. Run the game
   ```bash
   java TicTacToe
   ```

## How to Play

### Basic Gameplay

- The game board is a 3x3 grid.
- Players take turns to place their marks on the board.
- The first player to get three of their symbols in a row (horizontally, vertically, or diagonally) wins the game.

### Input Method

- Rows and columns are numbered from 1 to 3.
- Enter the position in the format `(x,y)` where `x` is the row number and `y` is the column number.
- Example: to place a symbol in the first row and first column, enter `(1,1)`.
- The input must not contain any spaces; otherwise, the program will prompt the player to re-enter the position.

### Advanced and CPU Options

- **Advanced Option**: players can enter their names and choose the mark they want to play with from a list of possible symbols.
- **CPU Option**: play against the computer.
  - In 1-player mode, the player can enter their name and choose their mark as in the advanced option.
  - In 2-player mode, the game defaults to the advanced option.

## Authors
Alfonso Cecilia Martínez and [Daniel Lozano Uceda](https://github.com/DanielVegetto)
