import java.util.Scanner;
public class TicTacToe {
	//Initialize the board with empty spaces
	public static void iniBoard(char[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}
	//Printing the board
	public static void printBoard(char[][] board) {
		System.out.println("+-----------+");
		for(int i = 0; i < board.length; i++) {
			System.out.print("|");
			for(int j = 0; j < board[i].length; j++) {
				System.out.print(" " + board[i][j] + " |");
			}
			System.out.println();
			System.out.println("+-----------+");
		}
	}
	public static boolean checkallPos(char[][] board, String position, char symbolplayerX, char symbolplayerNought) {
		int[] pos = new int[2];
		String[] positionparts;
		//First, we check the first element introduced by the user is an open bracket, the last element is a close bracket and if the string contains a comma. If so, It will return true. If not, it will return false.
		if(position.charAt(0) == '(' && position.charAt(position.length() - 1) == ')' && position.contains(",")) {
			//What we really need are the two integer numbers that indicates the position which the user wants to place the piece in
			position = position.replace("(", ""); //So we remove the open bracket
			position = position.replace(")", ""); //Then, we remove the close bracket
			positionparts = position.split(","); //After that, the string is now int,int so we split the string in two around the comma and store the result in an array
			//Now we check if the elements of the array are integer numbers
			for(int i = 0; i < positionparts.length; i++) {
				for(int j = 0; j < positionparts[i].length(); j++) {
					if(!Character.isDigit(positionparts[i].charAt(j)))
						return false; //If the elements stored in the array are not integers, the method will return else
				}
			}
		}
		else
			return false; //If one of the conditions of the first if is not fulfilled, the method will return false
		pos[0] = Integer.parseInt(positionparts[0]) - 1;
		pos[1] = Integer.parseInt(positionparts[1]) - 1;
		//Check if the position introduced is in the gameboard
		if (pos[0] >= 0 && pos[0] < board.length && pos[1] >= 0) {
			if (board[pos[0]][pos[1]] == symbolplayerX || board[pos[0]][pos[1]] == symbolplayerNought) //Now, it will check if the position is already taken
				return false;
		}
		else
			return false;
		return true;
	}
	//Assign a value in the board, I mean, insert one piece on the board
	public static char[][] assignvalue(char[][] board, String position, char currentPlayer, char symbolplayerX, char symbolplayerNought) {
		int[] pos = new int[2];
		if(position.charAt(0) == '(' && position.charAt(position.length() - 1) == ')' && position.contains(",")) {
			position = position.replace("(", "");
			position = position.replace(")", "");
			String[] positionparts = position.split(",");
			pos[0] = Integer.parseInt(positionparts[0]) - 1;
			pos[1] = Integer.parseInt(positionparts[1]) - 1;
		}
		if(currentPlayer == 'X')
			board[pos[0]][pos[1]] = symbolplayerX;
		else
			board[pos[0]][pos[1]] = symbolplayerNought;
		return board;
	}
	//Check if the board is full in order to continue or finish the game
	public static boolean fullBoard(char[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == ' ')
					return false;
			}
		}
		return true;
	}	
	//Check wins-draws-losses
	public static boolean checkWin(char[][] board) {
		//Checking the rows
		for(int i = 0; i < board.length; i++) {
			if(board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2])
				return true;
		}
		//Checking the columns
		for(int i = 0; i < board.length; i++) {
			if(board[0][i] != ' ' && board[0][i] == board[1][i] && board[0][i] ==board[2][i])
				return true;
		}
		//Checking the diagonals
		for(int i = 0; i < board.length; i++) {
			if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] ==board[2][2] || board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] ==board[2][0])
				return true;
		}
		return false;
	}
	//Prints all the available symbols
	public static void printsymbols(char[] symbols) {
		for(int i = 0; i < symbols.length; i++)
			System.out.print(symbols[i] + " ");
		System.out.println(")\nWARNING: After a symbol is selected, it cannot be changed during the game");
	}
	//Check if the symbol selected by the user is valid
	public static boolean checkSelectedSymbol(char[] symbols, char symbol) {
		for(int i = 0; i < symbols.length; i++) {
			if(symbol == symbols[i])
				return true;
		}
		return false;
	}
	//Describes the behaviour of the tic tac toe
	public static void TTT(char[][] board, String playerX, String playerNought, int playerCrossWins, int playerNoughWins, int draws, int placedpieces, char currentPlayer, char symbolplayerX, char symbolplayerNought, int gameMode, int mode) {
		Scanner input = new Scanner(System.in);
		String position;
		String repeat;
		do {
			System.out.println("A Tic-Tac-Toe game is going to start. " + playerX + " starts and next" + playerNought + " until the game finishes. You can play as many games as you wish.");
			do {
				iniBoard(board);
				printBoard(board);
				System.out.println("Placed Pieces: " + placedpieces);
				System.out.println(playerX + ", please specify the position where you want to place a piece, taking into account that the board is 3x3, e.g. (2,3).");
				position = input.nextLine();
				if(checkallPos(board, position, symbolplayerX, symbolplayerNought) == false)
					System.out.println("The position was incorrectly written or is already taken or is out of the boundaries of the board");
			}
			while(checkallPos(board, position, symbolplayerX, symbolplayerNought) == false);
			assignvalue(board, position, currentPlayer, symbolplayerX, symbolplayerNought);
			printBoard(board);
			placedpieces++;
			System.out.println("Placed Pieces: " + placedpieces);
			if(currentPlayer == 'X')
				currentPlayer = '0';
			else
				currentPlayer = 'X';
			while(fullBoard(board) == false) {
				do {
					if(currentPlayer == 'X') {
						System.out.println(playerX + ", please specify your play; remember that the board is 3x3:");
						position = input.nextLine();
					}
					else {
						System.out.println(playerNought + ", please specify your play; remember that the board is 3x3:");
						if(gameMode == 2 && mode == 0) {
							position = "(" + (1 + (int)(Math.random() * 3)) + "," + (1 + (int)(Math.random() * 3)) + ")";
							System.out.println(position);
						}
						else
							position = input.nextLine();
					}
					if(checkallPos(board, position, symbolplayerX, symbolplayerNought) == false) {
						System.out.println("The position was incorrectly written or is already taken or is out of the boundaries of the board");
						printBoard(board);
						System.out.println("Placed Pieces: " + placedpieces);
					}
				}
				while(checkallPos(board, position, symbolplayerX, symbolplayerNought) == false);
				assignvalue(board, position, currentPlayer, symbolplayerX, symbolplayerNought);
				printBoard(board);
				placedpieces++;
				System.out.println("Placed Pieces: " + placedpieces);
				if(checkWin(board) == true) {
					if(currentPlayer == 'X') {
						System.out.println(playerX + " has won.");
						playerCrossWins++;
					}	
					else {
						System.out.println(playerNought + " has won.");
						playerNoughWins++;
					}
					break;
				}
				else if(fullBoard(board) == true && checkWin(board) == false) {
					System.out.println("The game was a draw");
					draws++;
					break;
				}
				if(currentPlayer == 'X')
					currentPlayer = '0';
				else
					currentPlayer = 'X';
			}
			placedpieces = 0;
			currentPlayer = 'X';
			do {
				System.out.println("Do you want to play another game?");
				repeat = input.nextLine();
			}
			while(repeat.equalsIgnoreCase("yes") == false && repeat.equalsIgnoreCase("no") == false);
			System.out.println("So far, " + playerX + " has won " + playerCrossWins + " game(s), " + playerNought + " has won " + playerNoughWins + " game(s), and there has been " + draws + " draw(s).");
		}
		while(repeat.equalsIgnoreCase("yes"));
	}
	//This the main program of the game
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);		
		//Variables for the game status and functionality
		char currentPlayer = 'X'; //It is X or 0
		String playerX; //The name of the player X
		String playerNought; //The name of the player 0
		char symbolplayerX;
		char symbolplayerNought;
		int placedpieces = 0; //This counts the number of pieces in the board
		//The set of symbols that the user can select in advanced and CPU mode
		char[] symbols = {'X', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'Z', '+', '*', '-', '@', '1'};
		int playerCrossWins = 0; //It counts the number of times the player X has won
		int playerNoughWins = 0; //It counts the number of times the player 0 has won
		int draws = 0; //It counts the number of draws
		int gameMode; // 0 basic, 1 advanced, 2 cpu
		int mode = 0; //0 for 1- player mode or 1 for 2-player mode
		char[][] board = new char[3][3]; //This is the board
		System.out.println("This is the TIC TAC TOE game.");
		//A do-while for the selection of the game mode
		do {
			System.out.println("Select the game configuration:\n  Basic (0)\n  Advanced (1)\n  CPU (2)");
			gameMode = input.nextInt();
			if(gameMode != 0 && gameMode != 1 && gameMode != 2)
				System.out.println("Mode not valid. Please, do it again.");
		}
		while(gameMode != 0 && gameMode != 1 && gameMode != 2);
		switch(gameMode) {
			case 0:
				playerX = "Player X";
				playerNought = "Player 0";
				symbolplayerX = 'X';
				symbolplayerNought = '0';
				TTT(board, playerX, playerNought, playerCrossWins, playerNoughWins, draws, placedpieces, currentPlayer, symbolplayerX, symbolplayerNought, gameMode, mode);
				break;
			case 1:
				System.out.print("Player X, introduce your name: ");
				playerX = input.next();
				System.out.print("Player 0, introduce your name: ");
				playerNought = input.next();
				do {
					System.out.println(playerX + ", select a symbol between these:");
					printsymbols(symbols);
					symbolplayerX = input.next().charAt(0);
					if(checkSelectedSymbol(symbols, symbolplayerX) == false)
						System.out.println("The symbol selected is not valid:");
				}
				while(checkSelectedSymbol(symbols, symbolplayerX) == false);
				do {
					System.out.println(playerNought + ", select a symbol between these:");
					printsymbols(symbols);
					symbolplayerNought = input.next().charAt(0);
					if(checkSelectedSymbol(symbols, symbolplayerNought) == false)
						System.out.println("The symbol selected is not valid:");
				}
				while(checkSelectedSymbol(symbols, symbolplayerNought) == false);
				TTT(board, playerX, playerNought, playerCrossWins, playerNoughWins, draws, placedpieces, currentPlayer, symbolplayerX, symbolplayerNought, gameMode, mode);
				break;
			case 2:
				do {
					System.out.println("Select the mode\n  1-player mode (0)\n  2-player mode (1)");
					mode = input.nextInt();
					if(mode != 0 && mode != 1)
						System.out.println("The mode selected is not valid. Please, try again.");
				}
				while(mode != 0 && mode != 1);
				if(mode == 0) {
					System.out.print("Player X, introduce your name: ");
					playerX = input.next();
					playerNought = "Player 0";
					do {
						System.out.println(playerX + ", select a symbol between these:");
						printsymbols(symbols);
						symbolplayerX = input.next().charAt(0);
						if(checkSelectedSymbol(symbols, symbolplayerX) == false)
							System.out.println("The symbol selected is not valid:");
					}
					while(checkSelectedSymbol(symbols, symbolplayerX) == false);
					symbolplayerNought = '0';
					TTT(board, playerX, playerNought, playerCrossWins, playerNoughWins, draws, placedpieces, currentPlayer, symbolplayerX, symbolplayerNought, gameMode, mode);
				}
				else if(mode == 1) {
					System.out.print("Player X, introduce your name: ");
					playerX = input.next();
					System.out.print("Player 0, introduce your name: ");
					playerNought = input.next();
					do {
						System.out.println(playerX + ", select a symbol between these:");
						printsymbols(symbols);
						symbolplayerX = input.next().charAt(0);
						if(checkSelectedSymbol(symbols, symbolplayerX) == false)
							System.out.println("The symbol selected is not valid:");
					}
					while(checkSelectedSymbol(symbols, symbolplayerX) == false);
					do {
						System.out.println(playerNought + ", select a symbol between these:");
						printsymbols(symbols);
						symbolplayerNought = input.next().charAt(0);
						if(checkSelectedSymbol(symbols, symbolplayerNought) == false)
							System.out.println("The symbol selected is not valid:");
					}
					while(checkSelectedSymbol(symbols, symbolplayerNought) == false);
					TTT(board, playerX, playerNought, playerCrossWins, playerNoughWins, draws, placedpieces, currentPlayer, symbolplayerX, symbolplayerNought, gameMode, mode);
				}
				break;
		}
	}
}