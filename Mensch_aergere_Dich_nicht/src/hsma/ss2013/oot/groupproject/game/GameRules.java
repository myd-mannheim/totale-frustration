package hsma.ss2013.oot.groupproject.game;

import hsma.ss2013.oot.groupproject.board.Board;
import hsma.ss2013.oot.groupproject.board.Field;
import hsma.ss2013.oot.groupproject.board.Token;
import hsma.ss2013.oot.groupproject.player.Player;

import java.util.ArrayList;

public class GameRules {

    private static Move lastMove = null; // Wenn letzter angewandter MoveType
					 // "START" war, wird alles gel�scht
					 // au�er Move vom Startfeld
    private static GameRules rules = new GameRules();

    public static GameRules getInstance() {
	return rules;
    }

    public ArrayList<Move> possibleMoves(int diceRoll, Player player,
	    Board board) {
	ArrayList<Move> pMoves = new ArrayList<>();
	ArrayList<Token> plTokens = new ArrayList<>();

	// Wenn im letzten Zug ein Token auf start gesetzt wurde
	// werden alle anderen Tokens ausser das auf dem Startfeld stehende
	// Token
	// aus der Liste entfernt
	if (lastMove != null) {
	    plTokens.add(lastMove.getToken());
	    lastMove = null;
	} else {
	    plTokens.addAll(player.getTokens());
	}

	for (int i = 0; i < plTokens.size(); i++) {

	    Token currToken = plTokens.get(i);
	    Move move = new NullMove(null, null, null); // Dummy Inhalt, damit
							// nicht null in die
							// ArrayList geschrieben
							// wird

	    if (isInHouse(currToken)) {
		if (isStartable(diceRoll)) {
		    if (!isStartBlocked(player, board)) {
			move = new Move(player, currToken, MoveType.START);
		    }
		} else {
		    move = new Move(player, currToken, MoveType.SUSPEND);
		}
	    } else if (isInHome(currToken)) {
		move = new Move(player, currToken, MoveType.SUSPEND);// MOVE_IN_HOME);
	    } else if (isFinishPossible(diceRoll, player, board, currToken)) {
		if (isHomeBlocked(diceRoll, player, board, currToken)) {
		    move = new Move(player, currToken, MoveType.SUSPEND);
		} else {
		    move = new Move(player, currToken, MoveType.FINISH);
		}
	    } else if (isBarrier(diceRoll, player, board, currToken)) {
		move = new Move(player, currToken, MoveType.SUSPEND);
	    } else if (isThrowable(diceRoll, player, board, currToken)) {
		move = new Move(player, currToken, MoveType.THROW);
	    } else if (isBarrierPossible(diceRoll, player, board, currToken)) {
		move = new Move(player, currToken, MoveType.BARRIER);

	    } else {
		move = new Move(player, currToken, MoveType.MOVE);
	    }
	    if (!(move instanceof NullMove))
		pMoves.add(move);
	}
	pMoves = deleteWaste(pMoves);

	return pMoves;

    }

    private boolean isHomeBlocked(int diceRoll, Player player, Board board,
	    Token currToken) {
	int remainingMoves = diceRoll - (40 - currToken.getMoves());
	if (currToken.getMoves() + diceRoll > 40) {
	    if (remainingMoves < 5) {
		if (board.field[player.getEndpoint()][remainingMoves].isEmpty()) {
		    return false;
		}
	    }
	}
	return true;
    }

    private ArrayList<Move> deleteWaste(ArrayList<Move> pMoves) {

	ArrayList<Move> singleMove = new ArrayList<>(); // Wenn nur ein einziger
							// Zug getan werden kann
	ArrayList<Move> throwMove = new ArrayList<>(); // Wenn mehrere W�rfe
						       // m�glich sind
	ArrayList<Move> finalMove = new ArrayList<>(); // Finalisierte Liste von
						       // Moves

	for (int i = 0; i < pMoves.size(); i++) {
	    if (pMoves.get(i).getMoveType() == MoveType.START) {
		singleMove.add(pMoves.get(i));
		lastMove = singleMove.get(0);
		return singleMove;
	    }

	    if (pMoves.get(i).getMoveType() == MoveType.THROW) {
		throwMove.add(pMoves.get(i));
	    } else if ((pMoves.get(i).getMoveType() != MoveType.SUSPEND)) {
		finalMove.add(pMoves.get(i));
	    }

	}

	if (throwMove.size() > 0) {
	    return throwMove;
	} else {
	    return finalMove;
	}

    }

    private boolean isStartBlocked(Player player, Board board) {
	Field startField = board.getField(player.getStartpoint());
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

    private boolean isInHome(Token currToken) {
	if (currToken.getMoves() > 40) {
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

	for (int i = currToken.getPosition() + 1; i <= futurePos; i++) {
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
	    futurePos = (futurePos % 40);
	}
	if (board.getField(futurePos).getToken().isEmpty()) {
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
	if (board.getField(futurePos).getToken().isEmpty()) {
	    return false;
	} else if (board.getField(futurePos).getToken().get(0).getOwner() == player) {
	    return true;
	} else {
	    return false;
	}
    }

    private boolean isFinishPossible(int diceRoll, Player player, Board board,
	    Token currToken) {
	if (currToken.getMoves() + diceRoll > 40) {
	    return true;
	}

	return false;
    }
}
