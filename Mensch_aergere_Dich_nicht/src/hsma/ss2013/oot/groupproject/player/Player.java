package hsma.ss2013.oot.groupproject.player;

import hsma.ss2013.oot.groupproject.board.Token;
import hsma.ss2013.oot.groupproject.game.Move;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Player {
	protected int startpoint;
	protected int endpoint;
	protected ArrayList<Token> myTokens;
	protected ArrayList<Token> home;
	protected char icon;
	protected String name;

	public Player(int startpoint, int endpoint, char icon, String name) {
		this.startpoint = startpoint;
		this.endpoint = endpoint;
		this.home = new ArrayList<>();
		this.myTokens = new ArrayList<>();
		this.icon = icon;
		this.name = name;
		createTokens(this);
	}

	private void setEndpoint() {
	    //endpoint = (startpoint
	    
	}

	public abstract Move chooseMove(ArrayList<Move> moves);

	/**
	 * @param player
	 */
	// War davor im Konstruktor
	private void createTokens(Player player) {
		AtomicLong idCounter = new AtomicLong();
		// Create Tokens and set to Home
		for (int i = 0; i < 4; i++) {
			Token tokenToAdd = new Token(-1, this, icon,
					(int) (idCounter.getAndIncrement()));
			myTokens.add(tokenToAdd);
			home.add(tokenToAdd);
		}
	}

	public int getEndpoint() {
		return endpoint;
	}

	public ArrayList<Token> getTokens() {
		return myTokens;
	}

	public ArrayList<Token> getHome() {
		return home;
	}

	public int getStartpoint() {
		return startpoint;
	}

	public String getName() {
		return name;
	}

	public boolean isFullHome() {
		if (home.size() == 4) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Fügt Token in die Homes hinzu
	 */
	
	public void moveIntoStart (Token token){
		home.add(token);
		
	}
	

}
