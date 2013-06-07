package hsma.ss2013.oot.groupproject.board;

public class Dice {
	private static Dice dice = new Dice(6);
	private int sides;

	private Dice(int sides) {
		this.sides = sides;
	}

	public static Dice getDice() {
		return dice;
	}

	protected int roll() {
		return (int) (Math.random() * (sides)) + 1;
	}

}
