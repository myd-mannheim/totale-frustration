package hsma.ss2013.oot.groupproject.player.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import hsma.ss2013.oot.groupproject.board.Token;
import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.game.MoveType;

public class AI {

    private MoveType[] moveTypeDefaultOrder = { MoveType.THROW,
	    MoveType.FINISH, MoveType.START, MoveType.MOVE, MoveType.SUSPEND };
    // die Reihenfolge spiegelt die Priorisierung wieder, ganz vorne [0] heiﬂt
    // hohe Prio

    private boolean[][] rating = new boolean[4][MoveType.values().length];

    public boolean[][] getRating(ArrayList<Move> moves) {

	Move[] moveArray = new Move[moves.size()];
	moves.toArray(moveArray);
	// Sortiere Array von Moves nach Token (Token ID)
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
	Token lastToken = null;
	int tokenRatingCounter = -1;
	// F¸hre f¸r jeden Token die Bewertung durch (setzten des entsprechenden
	// Arrayplatzes in rating[]
	for (int i = 0; i < moveArray.length; i++) {
	    if (!(lastToken.getID() == moveArray[i].getToken().getID())) {
		lastToken = moveArray[i].getToken();
		tokenRatingCounter++;
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
	}
	return this.rating;
    }


}
