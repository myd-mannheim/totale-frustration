package hsma.ss2013.oot.groupproject.game;

import game.MoveType;
import game.Player;
import hsma.ss2013.oot.groupproject.board.Token;

public class Move {
	protected Player owner;
	protected Token token;
	protected MoveType moveType;
	
	public Move(Player owner, Token token, MoveType moveType){
		this.owner = owner;
		this.token = token;
		this.moveType = moveType;
	}

	public Player getOwner() {
		return owner;
	}

	public Token getToken() {
		return token;
	}

	public MoveType getMoveType() {
		return moveType;
	}
	
}
