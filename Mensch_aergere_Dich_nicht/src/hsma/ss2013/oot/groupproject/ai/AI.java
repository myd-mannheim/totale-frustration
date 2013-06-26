package hsma.ss2013.oot.groupproject.ai;

import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.game.MoveType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Diese Klasse stellt die Implementierung f�r die k�nstliche Intelligenz
 * bereit.
 * 
 * @author moshpit, max
 * 
 */
public class AI {
	// die Reihenfolge spiegelt die Priorisierung wieder, Indexplatz [0] hei�t
	// hohe Prio
	private MoveType[] moveTypeDefaultOrder = { MoveType.THROW,
			MoveType.FINISH, MoveType.START, MoveType.MOVE, MoveType.BARRIER, MoveType.SUSPEND };

	// 2D-Array zur Bewertung aller moeglichen Zuege aller Tokens eines Spielers
	private boolean[][] rating = new boolean[4][MoveType.values().length];

	/**
	 * Methode zur Bestimmung des Ratings f�r Moves. Die Methode kopiert die
	 * �bergebene {@code ArrayList<Moves>} in ein {@code Move[]} -Array und
	 * sortiert dieses Array nach Token-IDs in der inneren anonymen Klasse �ber
	 * einen Comparator.
	 * 
	 * @param moves
	 * @return
	 */
	public boolean[][] createRatingForTokens(ArrayList<Move> moves) {
		Move[] moveArray = new Move[moves.size()];
		moves.toArray(moveArray);
		// Sortiere {@code ArrayList<Moves>} nach Token unter Ber�cksichtigung
		// der Token-ID)
		Arrays.sort(moveArray, new Comparator<Move>() {
			@Override
			public boolean equals(Object obj) {
				return super.equals(obj);
			}

			@Override
			public int compare(Move o1, Move o2) {
				if (o1.getToken().getID() == o2.getToken().getID()) {
					return 0;
				}
				if (o1.getToken().getID() > o2.getToken().getID()) {
					return 1;
				}
				if (o1.getToken().getID() < o2.getToken().getID()) {
					return -1;
				}
				return 42; // Die Antwort auf Alles!
			}
		});
		// LOOP 2: Es gibt max. 4 Tokens. tokenCount entspricht nacheinander der
		// ID
		// eines Token. Die ID der Tokens sind immer 0,1,2,3. Der Arrayplatz in
		// rating[][] ist die Token ID -1
		for (int tokenCount = 0; tokenCount <= 3; tokenCount++) {
			// Suche f�r jeden Token die Z�ge, die auf diesen Token verweisen
			for (int i = 0; i < moveArray.length; i++) {
				if (moveArray[i].getToken().getID() != tokenCount) {
					// break;
				} else {
					// Trage die m�glichen Z�ge dieses Token ins rating[][] ein
					switch (moveArray[i].getMoveType()) {
					case THROW:
						this.rating[tokenCount][0] = true;
						break;
					case FINISH:
						this.rating[tokenCount][1] = true;
						break;
					case START:
						this.rating[tokenCount][2] = true;
						break;
					case MOVE:
						this.rating[tokenCount][3] = true;
						break;
					case BARRIER:
					    	this.rating[tokenCount][4] = true;
					    	break;
					case SUSPEND:
						this.rating[tokenCount][5] = true;
						break;
					default:
						break;
					}
				}
			}
		}
		return this.rating;
	}

	/**
	 * Wertet die m�glichen Z�ge pro Token aus. Ein Aufruf ist nur nach dem
	 * Aufruf der Methode createRatingForTokens sinnvoll.
	 * 
	 * @param moves
	 * @return Move auf Grundlage des Ratings
	 */
	public Move getMoveByRating(ArrayList<Move> moves) {
		for (int i = 0; i < this.moveTypeDefaultOrder.length; i++) {
			for (int tokenCount = 0; tokenCount <= 3; tokenCount++) {
				if (this.rating[tokenCount][i] == true)
					return this.getMoveByMoveTypeAndTokenID(moves, tokenCount,
							moveTypeDefaultOrder[i]);
			}
		}
		return null;// should not happen at any time
	}

	/**
	 * Gibt aus einer Liste von Moves denjenigen zur�ck, der dem gesuchten
	 * MoveTyp und TokenID entspricht
	 * 
	 * @param moves
	 * @param tokenID
	 * @param moveType
	 * @return
	 */
	private Move getMoveByMoveTypeAndTokenID(ArrayList<Move> moves,
			int tokenID, MoveType moveType) {
		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i).getToken().getID() == tokenID
					&& moves.get(i).getMoveType() == moveType) {
				return moves.get(i);
			}
		}
		return null;// should not happen at any time
	}
}
