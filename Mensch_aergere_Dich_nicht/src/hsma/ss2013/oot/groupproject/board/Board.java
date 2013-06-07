package hsma.ss2013.oot.groupproject.board;



import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.player.Player;

import java.util.ArrayList;

public class Board {
	public Field[][] field;
	Player[] players;

	public Board(Player[] players) {
		this.players = players;
		field = new Field[40][1];
		field[9] = new Field[5];
		field[19] = new Field[5];
		field[29] = new Field[5];
		field[39] = new Field[5];
		init();

	}

	public void init() {
		int index = 0;

		// Creating Field

		for (int i = 0; i < field.length; i++) {
			field[i][0] = new Field(index);
			index++;
		}

		// Creating Players Houses(Goals)
		int playerNum = 1;
		for (int j = 9; j <= 39; j += 10) {
			Player currPlayer = null;
			if (playerNum < players.length) {
				currPlayer = players[playerNum];
			}

			for (int i = 1; i < 5; i++) {

				field[j][i] = new House(index, currPlayer);

				index++;
			}
			if (playerNum == 3) {
				playerNum = -1;
			}
			playerNum++;

		}

	}

	public Field[][] getFieldArray() {
		return field.clone();
	}

	public Field getField(int index) {
		if (index > 55) {
			System.out.println("größer als Spielfeld");
			return null;
		}
		if (index > 39) {
			int nIndex = index - 39;
			int row = 9;
			int offset = nIndex / 4;
			while (offset > 0) {
				row += 10;
				offset--;
			}
			if (nIndex % 4 == 0) {
				return field[row][0];
			} else {
				return field[row][nIndex % 4];
			}
		} else {
			return field[index][0];
		}
	}

	public Player[] getPlayers() {
		return players;
	}

	public void moveToken(Move move, int diceRoll) {

		switch (move.getMoveType()) {
		case THROW:
			break;
		case FINISH:
			break;
		case BARRIER:
			break;
		case MOVE:
			simpleMoveToken(move,diceRoll);
			break;
		case START:
			moveToStart(move);
			break;
		}

		// else {
		// int tokenMoves = getField(move.getIndex()).getToken().get(0).moves;
		// ArrayList<Token> tokens = getField(move.getIndex()).getToken();
		// Token tokenToMove = tokens.get(0);
		//
		// if (tokenMoves + diceRoll > 40) {
		//
		// tokens.remove(0);
		// int movesToDo = 40 - tokenMoves;
		// Field destination = getField(move.getIndex() + movesToDo);
		// destination.setToken(tokenToMove);
		// destination.setBarrier();
		// movesToDo = diceRoll - movesToDo;
		// destination = field[tokenToMove.getPosition()][movesToDo - 1];
		// destination.setToken(tokenToMove);
		// destination.setBarrier();
		//
		// } else {
		// tokens.remove(0);
		// getField(move.getIndex()).setBarrier();
		// Field destination = getField(move.getIndex() + diceRoll);
		// destination.setToken(tokenToMove);
		// destination.setBarrier();
		// tokenToMove.moves += diceRoll;
		// tokenToMove.move(move.getIndex() + diceRoll);
		// }
		// }
	}

	private void simpleMoveToken(Move move, int diceRoll) {
		int tokenPosition = move.getToken().getPosition();
		
		ArrayList<Token> tokens = getField(tokenPosition).getToken();
		
		Token tokenToMove = tokens.get(findToken(tokens, move.getToken()));
		
		tokens.remove(findToken(tokens, move.getToken()));
		
		getField(tokenPosition).setBarrier();
		
		Field destination = getField(tokenPosition + diceRoll);
		
		destination.setToken(tokenToMove);
		destination.setBarrier();
		tokenToMove.moves += diceRoll;
		tokenToMove.move(tokenPosition + diceRoll);

	}
	
	
	private int findToken(ArrayList<Token> tokens, Token token){
		if (tokens.get(0) == token){
			return 0;
		} else {
			return 1;
		}
		
	}

	private void moveToStart(Move move) {
		Field startField = this.getField(move.getOwner().getStartpoint());
		ArrayList<Token> tokenHome = move.getOwner().getHome();
		Token tokenToMove = tokenHome.get(0);
		tokenHome.remove(0);

		startField.setToken(tokenToMove);
		tokenToMove.moves++;
		tokenToMove.move(startField.getIndex());

	}

	public boolean isFullHouse() {
		boolean isEmpty = false;
		for (int i = 9; i <= 39; i += 10) {
			for (int j = 1; j < 5; j++) {
				if (field[i][j].isEmpty()) {
					isEmpty = true;
					break;
				}
			}
			if (isEmpty = true) {
				break;
			}
		}
		return !isEmpty;
	}
}
