package hsma.ss2013.oot.groupproject.board;

import hsma.ss2013.oot.groupproject.player.Player;

/**
 * Diese Klasse stellt die Implementierung für die einzelnen Spielfiguren eines
 * Spielers zur Verfügung.
 * 
 * @author moshpit
 * 
 */
public class Token {
    // Position auf dem Feld
    protected int position;
    protected Player owner;
    protected char icon;
    // Gelaufene Schritte
    protected int moves;
    protected int id;

    // TODO wenn geworfen moves counter auf 0 setzen!!!

    /**
     * @param pos
     * @param owner
     * @param icon
     * @param id
     *            Token-Id (nur für die Auswertung in {@code AI} gedacht)
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

    /**
     * Setzt eine Figur auf eine bestimmtes Feld
     * 
     * @param newPosition
     *            - neue Position
     */
    public void moveTo(int newPosition) {
	this.position = newPosition;
    }

    /**
     * Erhöht die gelaufenen Schritte einer Figur
     * 
     * @param steps
     */
    public void addMoves(int steps) {
	this.moves += steps;
    }

    public int getID() {
	return id;
    }

    /**
     * Liefert die restlichen nötigen Schritte bis zum Homefeld
     * 
     * @return Restschritte bis zum Homefeld
     */
    public int getRestSteps() {
	return (40 - this.moves);
    }

    /**
     * Setzt den Schrittecounter der Figur auf 0
     */
    public void setMovesToNull() {
	this.moves = 0;
    }

}
