
public class Main {

	public static void main(String[] args) {
		new TicTacToeGame(null)
		.setGameInterface(new TicTacToeGame.GameInterface() {			
			@Override
			public int moveMade(String[][] gameBoard, int player) {
				// Based on the gameBoard[][] 
				// and player who made the move
				// determine who won the game

				String symbol = (player == 1) ? "X" : "O";

				for (int i = 0; i < 3; i++) {
					if ((gameBoard[i][0].equals(symbol) && gameBoard[i][1].equals(symbol) && gameBoard[i][2].equals(symbol)) ||
									(gameBoard[0][i].equals(symbol) && gameBoard[1][i].equals(symbol) && gameBoard[2][i].equals(symbol)) ||
									(gameBoard[0][0].equals(symbol) && gameBoard[1][1].equals(symbol) && gameBoard[2][2].equals(symbol)) ||
									(gameBoard[0][2].equals(symbol) && gameBoard[1][1].equals(symbol) && gameBoard[2][0].equals(symbol))) {
						if (player == 1) {
							return 1;
						} else {
							return 2;
						}
					} else if (!gameBoard[0][0].isEmpty() && !gameBoard[0][1].isEmpty() && !gameBoard[0][2].isEmpty() &&
									!gameBoard[1][0].isEmpty() && !gameBoard[1][1].isEmpty() && !gameBoard[1][2].isEmpty() &&
									!gameBoard[2][0].isEmpty() && !gameBoard[2][1].isEmpty() && !gameBoard[2][2].isEmpty()) {
						return 0;
					}
				}

				for(int i = 0; i<3;i++) {
					for(int j = 0; j<3;j++) {
						System.out.printf("[%s]\t", gameBoard[i][j]); 
					}
					System.out.println();
				}
				System.out.printf(" player : %d\n", player);
				
				// return 1 if X wins
				// return 2 if O wins
				// return 0 if draw
				return -1;  
			}
		});
	}

}
