package hsma.ss2013.oot.groupproject.player;

import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.ui.GameIO;

import java.util.ArrayList;

public class HumanPlayer extends Player {

    public HumanPlayer(int startpoint, int endpoint, char icon, String name) {
	super(startpoint, endpoint, icon, name);
    }

    @Override
    public Move chooseMove(ArrayList<Move> moves) {
	return GameIO.getPlayerMove(moves);
    }
}
