package hsma.ss2013.oot.groupproject.player;

import hsma.ss2013.oot.groupproject.ai.AI;
import hsma.ss2013.oot.groupproject.game.Move;

import java.util.ArrayList;

/**
 * Diese Klasse stellt die Implementierung für computergesteuerte Spieler 
 * zur Verfügung.
 * @author moshpit
 * 
 */
public class AIPlayer extends Player {

	public AIPlayer(int startpoint, int endpoint, char icon, String name) {
		super(startpoint, endpoint, icon, name);
	}

	@Override
	public Move chooseMove(ArrayList<Move> moves) {
		//return GameIO.getPlayerMove(moves);
		AI ai = new AI();
		ai.createRatingForTokens(moves);
		return ai.getMoveByRating(moves);
	}

}
