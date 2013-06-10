package hsma.ss2013.oot.groupproject.game;


import hsma.ss2013.oot.groupproject.board.Token;
import hsma.ss2013.oot.groupproject.player.Player;

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
