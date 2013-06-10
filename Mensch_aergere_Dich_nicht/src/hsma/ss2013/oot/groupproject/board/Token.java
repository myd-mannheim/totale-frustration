package hsma.ss2013.oot.groupproject.board;

import hsma.ss2013.oot.groupproject.player.Player;

public class Token {
    protected int position;
    protected Player owner;
    protected char icon;
    protected int moves;

    // TODO wenn geworfen moves counter auf 0 setzen!!!

    public Token(int pos, Player owner, char icon) {
	this.position = pos;
	this.owner = owner;
	this.icon = icon;
	this.moves = 0;
    }

    public int getMoves() {
	return moves;
    }

    public Player getOwner() {
	return owner;
    }

    public char getIcon() {
	return this.icon;
    }

    public int getPosition() {
	return position;
    }

    public void move(int newPosition) {
	this.position = newPosition;
    }

    public void addMoves(int moves) {
	this.moves += moves;
    }

}
