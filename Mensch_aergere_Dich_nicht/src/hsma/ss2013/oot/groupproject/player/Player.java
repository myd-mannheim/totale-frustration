package hsma.ss2013.oot.groupproject.player;

public abstract class Player {

    private String name;
    private Piece[] homeRow;
    private Piece[] outArea;

    public int dice() {
	//TODO Implementierung
	return 0;
    }

    public void putPice(Piece piece) {
	//TODO Implementierung

    }

    public String getName() {
	return name;
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
