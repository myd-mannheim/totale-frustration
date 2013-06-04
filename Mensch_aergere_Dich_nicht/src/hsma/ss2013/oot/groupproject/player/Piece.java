package hsma.ss2013.oot.groupproject.player;

public class Piece {

    private String position;
    private Colour colour;
    private Number number;
    private Player affiliation;

    public String getPosition() {
	return position;
    }

    public void setPosition(String position) {
	this.position = position;
    }

    public Colour getColour() {
        return colour;
    }

    public Number getNumber() {
        return number;
    }

    public Player getAffiliation() {
        return affiliation;
    }

}
