package hsma.ss2013.oot.groupproject.player;

import hsma.ss2013.oot.groupproject.ai.AI;
import hsma.ss2013.oot.groupproject.game.Move;

import java.util.ArrayList;

/**
 * Diese Klasse stellt die Implementierung f�r computergesteuerte Spieler 
 * zur Verf�gung.
 * @author moshpit
 * 
 */
public class AIPlayer extends Player {

	public AIPlayer(int startpoint, int endpoint, char icon, String name) {
		super(startpoint, endpoint, icon, name);
	}

	@Override
	public Move chooseMove(ArrayList<Move> moves) {
		AI ai = new AI();
		ai.createRatingForTokens(moves);
		Move move = ai.getMoveByRating(moves);
		return move;
	}

}
