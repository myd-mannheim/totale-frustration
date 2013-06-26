package hsma.ss2013.oot.groupproject.board;

import hsma.ss2013.oot.groupproject.player.Player;

public class House extends Field {
    protected Player owner;

    public House(int index, Player owner) {
	super(index);
	this.owner = owner;
    }

    public Player getOwner() {
	return this.owner;
    }

}
