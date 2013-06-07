package hsma.ss2013.oot.groupproject.game;

import game.Field;
import game.Move;
import game.MoveType;
import game.Player;
import hsma.ss2013.oot.groupproject.board.Board;
import hsma.ss2013.oot.groupproject.board.Token;

import java.util.ArrayList;

public class GameRules {
	private static GameRules rules = new GameRules();

	public static GameRules getInstance() {
		return rules;
	}

	public ArrayList<Move> possibleMoves(int diceRoll, Player player,
			Board board) {
		ArrayList<Move> pMoves = new ArrayList<>();
		ArrayList<Token> plTokens = player.getTokens();
		for (int i = 0; i < plTokens.size(); i++) {

			Token currToken = plTokens.get(i);
			Move move = null;

			if (isInHouse(currToken)) {
				if (isStartable(diceRoll)) {
					if (!isStartBlocked(player, board)) {
						move = new Move(player, currToken, MoveType.START);

					}
				} else {
					move = new Move(player, currToken, MoveType.SUSPEND);
				}
			} else if (isBarrier(diceRoll, player, board, currToken)) {
				move = new Move(player, currToken, MoveType.SUSPEND);
			} else if (isThrowable(diceRoll, player, board, currToken)) {
				move = new Move(player, currToken, MoveType.THROW);
			} else if (isBarrierPossible(diceRoll, player, board, currToken)) {
				move = new Move(player, currToken, MoveType.BARRIER);
			} else if (isFinishPossible(diceRoll, player, board, currToken)) {
				move = new Move(player, currToken, MoveType.FINISH);
			} else {
				move = new Move(player, currToken, MoveType.MOVE);
			}
			pMoves.add(move);

		}
		
		System.out.println(pMoves.size()+"!!!!");
		pMoves = deleteWaste(pMoves);

		return pMoves;

	}

	private ArrayList<Move> deleteWaste(ArrayList<Move> pMoves) {
		for (int i = 0; i < pMoves.size(); i++) {
			if (pMoves.isEmpty()) {
				break;
			}
			if (pMoves.get(i).getMoveType() == MoveType.THROW) {
				ArrayList<Move> singleMove = new ArrayList<>();
				singleMove.add(pMoves.get(i));
				return singleMove;
			}
			if (pMoves.get(i).getMoveType() == MoveType.SUSPEND) {
				pMoves.remove(i);
				i--;
			}

		}
		return pMoves;
	}

	private boolean isStartBlocked(Player player, Board board) {
		Field startField = board.getField(player.startpoint);
		if (startField.isBarrier()) {
			return true;
		} else if (startField.getToken().size() == 0) {
			return false;
		} else if (startField.getToken().get(0).getOwner() == player) {
			return true;
		}
		return false;

	}

	private boolean isStartable(int diceRoll) {
		if (diceRoll == 6) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isInHouse(Token currToken) {
		if (currToken.getPosition() == -1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isBarrier(int diceRoll, Player player, Board board,
			Token currToken) {
		int futurePos = currToken.getPosition() + diceRoll;

		if (futurePos > 39) {
			futurePos = futurePos % 39;
		}

		for (int i = currToken.getPosition(); i <= futurePos; i++) {
			if (i == 40) {
				i = 0;
			}
			if (board.getField(i).isBarrier()) {
				return true;
			}
		}
		return false;

	}

	private boolean isThrowable(int diceRoll, Player player, Board board,
			Token currToken) {
		int futurePos = diceRoll + currToken.getPosition();
		if (futurePos > 39) {
			futurePos = futurePos % 39;
		}
		if (board.getField(futurePos).getToken() != null) {
			return false;
		} else if (board.getField(futurePos).getToken().get(0).getOwner() != player) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isBarrierPossible(int diceRoll, Player player, Board board,
			Token currToken) {
		int futurePos = diceRoll + currToken.getPosition();
		if (futurePos > 39) {
			futurePos = futurePos % 39;
		}
		if (board.getField(futurePos).getToken() != null) {
			return false;
		} else if (board.getField(futurePos).getToken().get(0).getOwner() == player) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isFinishPossible(int diceRoll, Player player, Board board,
			Token currToken) {
		int remainingMoves = diceRoll - (40 - currToken.getMoves());
		if (currToken.getMoves() + diceRoll > 40) {
			if (remainingMoves < 4) {
				System.out.println(player.getEndpoint() +" " + remainingMoves);
				if (board.getField(player.getEndpoint() + remainingMoves)
						.isEmpty()) {
					return true;
				}

			}
		}
		return false;

	}

}
