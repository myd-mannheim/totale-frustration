package hsma.ss2013.oot.groupproject.board;

import hsma.ss2013.oot.groupproject.player.Player;

/**
 * Diese Klasse stellt die Implementierung für die einzelnen 
 * Spielfiguren eines Spielers zur Verfügung.
 * @author moshpit
 *
 */
public class Token {
    protected int position;
    protected Player owner;
    protected char icon;
    protected int moves;
    protected int id;

    // TODO wenn geworfen moves counter auf 0 setzen!!!

    /**
     * @param pos
     * @param owner
     * @param icon
     * @param id Token-Id (nur für die Auswertung in {@code AI} gedacht)
     */
    public Token(int pos, Player owner, char icon, int id) {
	this.position = pos;
	this.owner = owner;
	this.icon = icon;
	this.moves = 0;
	this.id = id;

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
    
    public int getID() {
	return id;
    }

}
