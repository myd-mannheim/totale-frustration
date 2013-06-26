package hsma.ss2013.oot.groupproject.test;

import hsma.ss2013.oot.groupproject.board.DiceAccess;


public class DiceTestDouble implements DiceAccess {
	
	private int rollResult = 1;
	
	public void setNextRollResult(int rollResult){
		this.rollResult = rollResult;
	}

	@Override
	public int roll() {
		return this.rollResult;
	}
	
	

}
