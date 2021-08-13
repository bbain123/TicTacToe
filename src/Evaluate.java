/* CS2210a
 * Brendan Bain
 * 251086487
*/
public class Evaluate {
	int boardRow, boardColumn, tilesForWin, difficulty;
	char[][] gameBoard;
	Dictionary dictionary;
	
	public Evaluate(int boardRows, int boardColumns, int tilesNeeded, int maxLevels) {
		boardRow = boardRows;
		boardColumn = boardColumns;
		tilesForWin = tilesNeeded;
		difficulty = maxLevels;
		gameBoard = new char[boardRow][boardColumn];
		
		for (int i = 0; i < boardRow; i++) {			//initialize gameBoard to empty
			for(int j = 0; j <boardColumn; j++) {
				gameBoard[i][j] = 'g';
			}
		}
	}
	
	public Dictionary createDictionary() {
		return  dictionary = new Dictionary(7759);
	}
	
	//method to calculate a string of gameBoards characters
	private String boardToString(char[][] board, int numRows, int numCols) {
		String result = "";
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				result = result + board[i][j];
			}
		}
		return result;
	}
	
	
	public Data repeatedConfig(Dictionary dict) {
		return dict.get(boardToString(gameBoard, boardRow, boardColumn));
	}
	
	
	public void insertConfig(Dictionary dict, int score, int level) {
		dict.put(new Data(boardToString(gameBoard, boardRow, boardColumn), score, level));
	}
	
	
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == 'g')
			return true;
		else
			return false;
	}
	
	public boolean tileOfComputer(int row, int col) {
		if (gameBoard[row][col] == 'o')
			return true;
		else
			return false;
	}
	
	public boolean tileOfHuman(int row, int col) {
		if(gameBoard[row][col] == 'b')
			return true;
		else
			return false;
	}
	
	public boolean wins(char symbol) {
		int succession = 0, maxSuccession = 0;
		char prevValue, currentValue;
		
		//ROW CHECK
		for (int i = 0; i < boardRow; i++) {
			prevValue = ' ';
			for(int j = 0; j < boardColumn; j++) {
				currentValue = gameBoard[i][j];
				if (currentValue == symbol && prevValue == symbol)
					succession++;
				else
					succession = 0;
				if (succession > maxSuccession)
					maxSuccession = succession;
				prevValue = currentValue;
			}
			succession = 0;
		}
		
		
		//COLUMN CHECK
		
		for (int j = 0; j < boardColumn; j++) {
			prevValue = ' ';
			for(int i = 0; i < boardRow; i++) {
				currentValue = gameBoard[i][j];
				if (currentValue == symbol && prevValue == symbol)
					succession++;
				else
					succession = 0;
				if (succession > maxSuccession)
					maxSuccession = succession;
				prevValue = currentValue;
			}
			succession = 0;
		}
		
		
		// DIAGONAL CHECK
																//checks from top left to bottom right diagonals v
			for(int j = boardColumn - 2; j > 0; j--) { 
				int rowCounter = 0;
				int colCounter = j;
				prevValue = ' ';
				while (colCounter < boardColumn && rowCounter < boardRow) {
					currentValue = gameBoard[rowCounter][colCounter];
					if(currentValue == symbol && prevValue == symbol)
						succession++;
					else
						succession = 0;
					if(succession > maxSuccession)
						maxSuccession = succession;
					rowCounter++;
					colCounter++;
					prevValue = currentValue;
				}
				succession = 0;
			}
			for(int i = 1; i < boardRow - 1; i++) { 
				int rowCounter = i;
				int colCounter = 0;
				prevValue = ' ';
				while (colCounter < boardColumn && rowCounter < boardRow) {
					currentValue = gameBoard[rowCounter][colCounter];
					if(currentValue == symbol && prevValue == symbol)
						succession++;
					else
						succession = 0;
					if(succession > maxSuccession)
						maxSuccession = succession;
					rowCounter++;
					colCounter++;
					prevValue = currentValue;
				}
				succession = 0;																				//^
			}
			//
			for (int i = 1; i < boardRow; i++) {			//checks bottom left to top right diagonals    v
				int rowCounter = i;
				int colCounter = 0;
				prevValue = ' ';
				while (colCounter < boardColumn && rowCounter >= 0) {
					currentValue = gameBoard[rowCounter][colCounter];
					if(currentValue == symbol && prevValue == symbol)
						succession++;
					else
						succession = 0;
					if(succession > maxSuccession)
						maxSuccession = succession;
					rowCounter--;
					colCounter++;
					prevValue = currentValue;
				}
				succession = 0;
			}
			
			for (int j = 1; j < boardColumn - 2; j++) {
				int rowCounter = boardRow - 1;
				int colCounter = j;
				prevValue = ' ';
				while (colCounter < boardColumn && rowCounter >=0) {
					currentValue = gameBoard[rowCounter][colCounter];
					if(currentValue == symbol && prevValue == symbol)
						succession++;
					else
						succession = 0;
					if(succession > maxSuccession)
						maxSuccession = succession;
					rowCounter--;
					colCounter++;
					prevValue = currentValue;
				}
				succession = 0;
			}																						//^
			
			if (maxSuccession + 1 >= tilesForWin)		//checks to see if there is a win
				return true;
			else
				return false;

	}
	
	public boolean isDraw() {
		for (int i = 0; i < boardRow; i++) {
			for(int j = 0; j < boardColumn; j++) {
				if (gameBoard[i][j] == 'g')
					return false;
			}
		}
		return true;
	}
	
	public int evalBoard() {
		if (wins('o'))
			return 3;
		
		if (wins('b'))
			return 0;
		
		if (isDraw())
			return 2;
		
			return 1;
	}
	
}


