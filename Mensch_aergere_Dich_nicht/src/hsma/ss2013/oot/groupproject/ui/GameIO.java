package hsma.ss2013.oot.groupproject.ui;

import hsma.ss2013.oot.groupproject.board.Board;
import hsma.ss2013.oot.groupproject.board.Field;
import hsma.ss2013.oot.groupproject.board.Token;
import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.player.HumanPlayer;
import hsma.ss2013.oot.groupproject.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameIO {

    public static void update(Board board) {
	Field[][] field = board.field;
	drawHomes(board);
	int row = 0;
	for (int r = 0; r < 4; r++) {

	    for (int i = row; i < (row + 10); i++) {

		Field curF = field[i][0];

		if (curF.isEmpty()) {
		    // System.out.printf("[ %d ]", curF.index);
		    System.out.printf("[ %d ]", curF.getIndex());
		} else {
		    ArrayList<Token> both = curF.getToken();

		    System.out.print("[ ");
		    for (Token e : both) {
			System.out.print(e.getIcon());
		    }
		    System.out.print(" ]");
		}
		if (i < 10) {
		    System.out.print(" ");
		}
	    }
	    row += 10;
	    System.out.println();

	    for (int j = 1; j < 5; j++) {
		System.out
			.print("                                                      ");
		// System.out.printf("| %s |", field[row - 1][j].index);
		System.out.printf("| %s |", field[row - 1][j].getIndex());

		System.out.println();
	    }
	    System.out.println();

	}

    }

    public static void drawHomes(Board board) {
	// Player[] players = board.players;
	Player[] players = board.getPlayers();
	System.out.println("Spieler H�user:");

	for (int i = 0; i < players.length; i++) {
	    System.out.print(players[i].getName() + ": [ ");
	    for (int j = 0; j < players[i].getHome().size(); j++) {
		System.out.print(players[i].getHome().get(j).getIcon() + " ");
	    }
	    System.out.print("]");
	    System.out.println();
	}

    }

    public static Move getPlayerMove(ArrayList<Move> moves) {

	Scanner eingabe1 = new Scanner(System.in);
	for (int i = 0; i < moves.size(); i++) {
	    System.out.printf("%d. Die Figur an der Stelle %d kann ", i + 1,
		    moves.get(i).getToken().getPosition());
	    switch (moves.get(i).getMoveType()) {
	    case SUSPEND:
		System.out.println("nicht bewegt werden");
		break;
	    case THROW:
		System.out.println("eine Figur schmei�en");
		break;
	    case FINISH:
		System.out.println("ins Haus r�cken");
		break;
	    case BARRIER:
		System.out.println("eine Barriere errichten");
		break;
	    case MOVE:
		System.out.println("ger�ckt werden");
		break;
	    case START:
		System.out.println("aufs Startfeld gesetzt werden");
		break;
	    }

	}
	System.out.print("Welchen Zug m�chten Sie durchf�hren?");
	int chosenMove = eingabe1.nextInt();
	return moves.get(chosenMove - 1);

    }

    public static Player[] gameStart() {
	Scanner eingabe = new Scanner(System.in);
	Scanner eingabeString = new Scanner(System.in);
	System.out.print("Wie viele Spieler spielen mit? ");
	int pCount = eingabe.nextInt();
	Player[] players = new Player[pCount];
	int startpoint = 0;
	int endpoint = 52;
	for (int i = 0; i < pCount; i++) {
	    System.out.printf("Name des %d. Spielers: ", i + 1);
	    String name = eingabeString.nextLine();
	    Player player = new HumanPlayer(startpoint, endpoint,
		    ((char) (i + 35)), name);
	    startpoint += 10;
	    if (i == 0) {
		endpoint = 40;
	    } else {
		endpoint += 4;
	    }
	    players[i] = player;
	}

	return players;
    }

    public static void noMoves() {
	Scanner eingabe = new Scanner(System.in);
	System.out.println();
	System.out.println("Keine Z�ge m�glich! (Mit Enter best�tigen)");
	eingabe.nextLine();

    }

    public static int printOptions() {
	Scanner eingabe = new Scanner(System.in);
	System.out.println("Welche Aktion möchten Sie durchführen?");
	System.out.println("1. Spielen");
	System.out.println("2. Regeln anzeigen");
	System.out.println("3. Spiel beenden");

	while (true) {
	    int number = eingabe.nextInt();
	    //TODO: Buchstaben abfangen
	    if (number < 1 || number > 3) {
		System.out
			.println("Bitte geben Sie eine Zahl zwischen 1 und 3 ein.");
	    } else {
		System.out.println("");
		return number;
	    }
	}
    }
    
    public static void printRules(){
	//TODO: Spielregeln schreiben
	System.out.println("Spielregeln:");
	System.out.println();
    }

}
