package hsma.ss2013.oot.groupproject.board;

import java.util.ArrayList;

public class Field {
    protected int index;
    protected ArrayList<Token> token;
    protected boolean barrier;

    public Field(int index) {
	this.index = index;
	this.token = new ArrayList<>();
    }

    public boolean isBarrier() {
	return barrier;
    }

    public void setBarrier() {
	if (this.token.size() == 2) {
	    barrier = true;
	}
    }

    public void setToken(Token token) {
	this.token.add(token);
    }

    public boolean isEmpty() {
	return token.isEmpty();
    }

    public int getIndex() {
	return index;
    }

    public ArrayList<Token> getToken() {
	return token;
    }

    public void deleteToken(Token token) {
	this.token.remove(token);
    }

}
