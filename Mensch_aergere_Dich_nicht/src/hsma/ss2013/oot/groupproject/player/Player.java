package hsma.ss2013.oot.groupproject.player;

public abstract class Player {

    private String name;
    private Piece[] homeRow;
    private Piece[] outArea;
    private Colour colour;
    private Number number;

    protected Player(String name, Colour colour, Number number) {
	this.name = name;
	this.colour = colour;
	this.number = number;
	outArea = new Piece[4];
	homeRow = new Piece[4];
	// TODO: 4 Spieler erstellen und in der homeRow speichern
    }

    public int dice() {
	// TODO Implementierung
	return 0;
    }

    public void putPice(Piece piece) {
	// TODO Implementierung

    }

    public String getName() {
	return name;
    }

    public Colour getColour() {
	return colour;
    }

    public Number getNumber() {
	return number;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Piece[] getHomeRow() {
	return homeRow;
    }

    public void setHomeRow(Piece[] homeRow) {
	this.homeRow = homeRow;
    }

    public Piece[] getOutArea() {
	return outArea;
    }

    public void setOutArea(Piece[] outArea) {
	this.outArea = outArea;
    }
}
