package hsma.ss2013.oot.groupproject.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import hsma.ss2013.oot.groupproject.board.Token;
import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.game.MoveType;

/**
 * Diese Klasse stellt die Implementierung für die künstliche Intelligenz
 * bereit.
 * 
 * @author moshpit
 * 
 */
public class AI {
    /*
     * die Reihenfolge spiegelt die Priorisierung wieder, Indexlpatz [0] heißt
     * hohe Prio
     */
    private MoveType[] moveTypeDefaultOrder = { MoveType.THROW,
	    MoveType.FINISH, MoveType.START, MoveType.MOVE, MoveType.SUSPEND };

    private boolean[][] rating = new boolean[4][MoveType.values().length];

    /**
     * Methode zur Bestimmung des Ratings für Moves. Die Methode kopiert die
     * übergebene {@code ArrayList<Moves>} in ein {@code Move[]} -Array und
     * sortiert dieses Array nach Token-IDs in der inneren anonymen Klasse über
     * einen Comparator.
     * 
     * @param moves
     * @return
     */
    public boolean[][] createRatingForTokens(ArrayList<Move> moves) {
	Move[] moveArray = new Move[moves.size()];
	moves.toArray(moveArray);
	/*
	 * Sortiere {@code ArrayList<Moves>} nach Token unter Berücksichtigung
	 * der Token-ID)
	 */
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
		return 0; // dummy, hier sollte man nie hinkommen
	    }
	});
	Token lastToken = moveArray[0].getToken();
	;
	int tokenRatingCounter = -1;
	// Führe für jeden Token die Bewertung durch (setzten des entsprechenden
	// Arrayplatzes in rating[]
	for (int i = 0; i < moveArray.length; i++) {
	    if (!(lastToken.getID() == moveArray[i].getToken().getID())) {
		lastToken = moveArray[i].getToken();
		tokenRatingCounter++;

		/*
		 * Setzt die entsprechenden Arrayplätze für jedes Token in
		 * {@code rating[]} in der Reihenfolge des Arrays {@code
		 * moveTypeDefaultOrder[]} auf true (= Spielzug möglich) oder
		 * false (= Spielzug nicht möglich). Wird für die spätere
		 * Auswertung / Priorisierung des {@link AIPlayer} verwendet.
		 */
		switch (moveArray[i].getMoveType()) {
		case THROW:
		    this.rating[tokenRatingCounter][0] = true;
		    break;
		case FINISH:
		    this.rating[tokenRatingCounter][1] = true;
		    break;
		case START:
		    this.rating[tokenRatingCounter][2] = true;
		    break;
		case MOVE:
		    this.rating[tokenRatingCounter][3] = true;
		    break;
		case SUSPEND:
		    this.rating[tokenRatingCounter][1] = true;
		    break;
		default:
		    break;
		}
	    }
	    System.out.println(moveArray[i].getToken().getID());

	}
	return this.rating;
    }

    // TODO Auswertung der Spielzüge und Priorisierung

}
