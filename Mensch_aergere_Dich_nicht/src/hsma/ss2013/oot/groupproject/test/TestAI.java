package hsma.ss2013.oot.groupproject.test;

import hsma.ss2013.oot.groupproject.board.Token;
import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.game.MoveType;
import hsma.ss2013.oot.groupproject.player.ai.AI;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestAI {
    
    ArrayList<Move> moves = new ArrayList<>();
    
    @Before
    public void setUp(){
	Token t1 = new Token(-1, null, (char)(0+35), 1);
	Token t2 = new Token(-1, null, (char)(0+35), 2);
	Token t3 = new Token(-1, null, (char)(0+35), 3);
	Token t4 = new Token(-1, null, (char)(0+35), 4);
	
	Move m1 = new Move(null, t1, MoveType.FINISH);
	Move m2 = new Move(null, t2, MoveType.FINISH);
	Move m3 = new Move(null, t3, MoveType.FINISH);
	Move m4 = new Move(null, t4, MoveType.FINISH);
	
	moves.add(m4);
	moves.add(m3);
	moves.add(m2);
	moves.add(m3);
	moves.add(m4);
	moves.add(m1);
	moves.add(m3);
	moves.add(m1);
	
	

    }
    
    @Test
    public void testMoveSorting(){
	new AI().getRating(moves);
	//for(int i=0;i<moves.size();i++){
	    //System.out.println(moves.get(i).getToken().getIcon());
	   // System.out.println(moves.get(i).getToken().getPosition());
	   // System.out.println(moves.get(i).getToken().getMoves());
	    //System.out.println(moves.get(i).getToken().getID());
	//}
    }

}
