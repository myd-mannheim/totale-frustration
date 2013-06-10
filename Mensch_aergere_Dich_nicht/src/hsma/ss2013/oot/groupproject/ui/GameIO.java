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
	System.out.println("Spieler Haeuser:");

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
		System.out.println("eine Figur schmeissen");
		break;
	    case FINISH:
		System.out.println("ins Haus ruecken");
		break;
	    case BARRIER:
		System.out.println("eine Barriere errichten");
		break;
	    case MOVE:
		System.out.println("gerueckt werden");
		break;
	    case START:
		System.out.println("aufs Startfeld gesetzt werden");
		break;
	    }

	}
	System.out.print("Welchen Zug moechten Sie durchfuehren?");
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
	System.out.println("Keine Zuege moeglich! (Mit Enter bestaetigen)");
	eingabe.nextLine();

    }

    public static int printOptions() {
	Scanner eingabe = new Scanner(System.in);
	System.out.println("Welche Aktion möchten Sie durchfuehren?");
	System.out.println("1. Spielen");
	System.out.println("2. Regeln anzeigen");
	System.out.println("3. Spiel beenden");

	while (true) {
	    int number = eingabe.nextInt();
	    // TODO: Buchstaben abfangen
	    if (number < 1 || number > 3) {
		System.out
			.println("Bitte geben Sie eine Zahl zwischen 1 und 3 ein.");
	    } else {
		System.out.println("");
		return number;
	    }
	}
    }

    public static void printRules() {
	System.out.println("Spielregeln:");
	System.out.println("");
	System.out
		.println("Wer eine Sechs wuerfelt, muss eine eigene Spielfigur aus der Startposition");
	System.out
		.println("heraus auf sein Startfeld des Spielfeldes stellen. Danach darf er erneut wuerfeln");
	System.out
		.println("und muss mit seiner Spielfigur der Augenanzahl des Wuerfels entsprechend viele Felder");
	System.out.println("vorruecken und damit das Startfeld freimachen.");
	System.out
		.println("Hat der Spieler jedoch keine Spielfigur in Startposition, d.h. alle seine Spielfiguren");
	System.out
		.println("sind im Spiel, kann er frei entscheiden, mit welcher seiner Spielfiguren er die erwuerfelten");
	System.out
		.println("sechs Spielfelder vorrueckt (auch die letzte Spielfigur, die auf dem Startfeld steht).");
	System.out
		.println("Auch dann darf der Spieler erneut wuerfeln und einen weiteren Zug machen.");
	System.out
		.println("Welche Spielfigur der Spieler beim erneuten Zug vorrueckt, bleibt ihm ueberlassen.");
	System.out.println("");
	System.out
		.println("Kommt beim Umlauf eine Spielfigur auf ein Feld zum Stehen, welches bereits");
	System.out
		.println("von einer gegnerischen Spielfigur besetzt ist, gilt die gegnerische Spielfigur als");
	System.out
		.println("geschlagen und muss zurueck ins Haeuschen. Eigene Figuren koennen nicht geschlagen werden.");
	System.out
		.println("Sind keine weiteren eigenen Spielfiguren im Spiel, kann der Zug somit nicht ausgefuehrt werden,");
	System.out.println("der naechste Spieler ist an der Reihe.");
	System.out
		.println("Hat ein Spieler mehrere Spielfiguren im Spielumlauf, darf dieser frei entscheiden, ");
	System.out.println("mit welcher Spielfigur er ziehen möchte.");
	System.out
		.println("Die Anzahl der Augen eines Wurfs duerfen nicht auf mehrere Spielfiguren verteilt werden.");
	System.out.println("");
	System.out.println("Sonderregeln:");
	System.out.println("");
	System.out
		.println("1. Hat ein Spieler ueberhaupt keine Spielfigur auf dem Spielfeld (trifft bei Spielbeginn auf");
	System.out
		.println("alle Spieler zu), so hat er drei Versuche eine Sechs zu wuerfeln, um eine Spielfigur ins Spiel");
	System.out.println("zu bringen.");
	System.out
		.println("2. Wer mit einer gewuerfelten Zahl eine Spielfigur eines Gegners schlagen kann, muss dies");
	System.out.println("ausfuehren (Schlagzwang).");
	System.out
		.println("3. Erreicht eine Spielfigur durch den Zug ein Feld, auf dem bereits eine eigene Figur steht,");
	System.out
		.println("so bilden diese beiden Figuren eine Barriere. Diese Barriere kann von keiner Spielfigur");
	System.out
		.println("uebersprungen werden (auch nicht von eigenen Spielfiguren). Die Spielfiguren, die die");
	System.out
		.println("Barriere bilden, koennen nicht geschlagen werden. Die Barriere bleibt so lange bestehen");
	System.out
		.println("bis eine Spielfigur weitergezogen wird bzw. werden muss.");
	System.out.println("");
	System.out.println("");
    }
}
