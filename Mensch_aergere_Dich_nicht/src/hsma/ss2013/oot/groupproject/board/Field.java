package hsma.ss2013.oot.groupproject.board;

import java.util.ArrayList;
import java.util.Iterator;

public class Field {
    protected int index;
    protected ArrayList<Token> tokenList;
    protected boolean barrier;
    private int xKoord;
    private int yKoord;

    public Field(int index) {
	this.index = index;
	this.tokenList = new ArrayList<>();
    }

    public boolean isBarrier() {
	return barrier;
    }

    public void setBarrier() {
	if (this.tokenList.size() == 2) {
	    barrier = true;
	}
    }

    public void setToken(Token token) {
	this.tokenList.add(token);
    }

    public boolean isEmpty() {
	return tokenList.isEmpty();
    }

    public int getIndex() {
	return index;
    }

    public ArrayList<Token> getToken() {
	return tokenList;
    }

    public void deleteToken(Token token) {
	this.tokenList.remove(token);
    }

    /**
     * Setzt die Koordinaten des Feldes, die zur Ausgabe benötigt werden
     * 
     * @param x
     *            X-Koordinate
     * @param y
     *            y-Koordinate
     */
    public void setFieldKoord(int x, int y) {
	this.xKoord = x;
	this.yKoord = y;
    }

    public int getXkoord() {
	return this.xKoord;
    }

    public int getyKoord() {
	return this.yKoord;
    }

    /**
     * Gibt die Icons der auf dem Feldstehenden Figuren als String zurück
     * 
     * @return Tokensicons auf dem Feld
     */
    public String tokensToString() {
	String a = "";
	Iterator<Token> iterator = this.getToken().iterator();
	while (iterator.hasNext()) {
	    Token tempToken = iterator.next();
	    a = a + tempToken.getIcon();
	    if (iterator.hasNext()) {
		a = a + ", ";
	    }
	}
	return a;
    }

}
