package hsma.ss2013.oot.groupproject.game;

import hsma.ss2013.oot.groupproject.board.Board;
import hsma.ss2013.oot.groupproject.board.Dice;
import hsma.ss2013.oot.groupproject.board.DiceAccess;
import hsma.ss2013.oot.groupproject.interfaces.MainMenu;
import hsma.ss2013.oot.groupproject.player.Player;
import hsma.ss2013.oot.groupproject.test.DiceTestDouble;
import hsma.ss2013.oot.groupproject.ui.GameIO;

import java.util.ArrayList;
import java.util.Scanner;

class Game implements MainMenu {

    protected void play() throws InterruptedException {
	// TODO: Wieso wirft die Methode diese Exception?
	Player[] players = GameIO.gameStart();
	Board board = new Board(players);
	GameIO.update(board);
	do {

	    for (int i = 0; i < players.length; i++) {
		Player player = players[i];
		System.out.printf("%s am Zug %n", player.getName());
		System.out.println();
		int addThrows = 0;
		if (player.isFullHome()) {
		    addThrows = 2;
		}

		while (addThrows >= 0) {
			
			DiceAccess dice = Dice.getDice();
			if(false){
				//New Dice for testing
				dice = new DiceTestDouble();
				System.out.println("Nächster Würfel");
				int next = new Scanner(System.in).nextInt();
				((DiceTestDouble)dice).setNextRollResult(next);
			}
		    int diceRoll = dice.roll();

		    System.out.println("Sie haben eine: " + diceRoll
			    + " gewuerfelt!");

		    ArrayList<Move> pMoves = GameRules.getInstance()
			    .possibleMoves(diceRoll, player, board);
		    if (pMoves.size() != 0) {
			Move chosenMove = player.chooseMove(pMoves);
			board.moveToken(chosenMove, diceRoll);
		    } else {
			GameIO.noMoves();
		    }
		    addThrows--;
		    if (diceRoll == 6) {
			addThrows = 0;
		    }
		    GameIO.update(board);
		}

	    }
	} while (!board.isFullHouse());
    }

    @Override
    public void startGame() {
	boolean playing = true;
	while (playing) {
	    int number = GameIO.printOptions();
	    switch (number) {
	    case 1:
		try {
		    play();
		} catch (InterruptedException ex) {
		    System.out.println("Sie haben das Spiel beendet.");
		}
		break;
	    case 2:
		GameIO.printRules();
		break;
	    case 3:
		playing = false;
		System.out.println("Sie haben das Spiel beendet.");
		break;
	    }
	}
    }
}
