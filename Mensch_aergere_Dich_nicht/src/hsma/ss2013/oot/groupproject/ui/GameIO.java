package hsma.ss2013.oot.groupproject.ui;

import hsma.ss2013.oot.groupproject.board.Board;
import hsma.ss2013.oot.groupproject.board.Field;
import hsma.ss2013.oot.groupproject.board.Token;
import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.player.AIPlayer;
import hsma.ss2013.oot.groupproject.player.HumanPlayer;
import hsma.ss2013.oot.groupproject.player.Player;
import hsma.ss2013.oot.groupproject.rules.DefinedGameRulesPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

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
	System.out.println();
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
	// TODO Eingabepruefung bei nur 1 moeglichen Option!
	// (IndexOutOfBoundsException)
	int chosenMove = eingabe1.nextInt();
	return moves.get(chosenMove - 1);
    }

    /**
     * Modifizierte {@link #gameStart()} mit Erfassung von Spielern des Typs
     * {@link HumanPlayer} und {@link AIPlayer}. Wird von {@link GameIO}
     * aufgerufen.
     * 
     * @return Ergebnis-Array der
     *         {@link #mergePlayerTypes(HumanPlayer[], AIPlayer[])} - Methode
     */
    public static Player[] gameStart() {
	return mergePlayerTypes(createHP(), createAIP());
    }

    /**
     * Private Methode fuer die Zusammenfuehrung der Objekte vom Typ HumanPlayer
     * und AIPlayer in ein gemeinsames Array. Wird nur innerhalb dieser Klasse
     * aufgerufen von {@link #gameStart()}.
     * 
     * Gibt das Array {} der menschlichen Spieler zurueck, wenn kein
     * Computergegner angemeldet wurde.
     * 
     * Gibt das Array der computergesteuerten Spieler zurueck, wenn kein
     * menschlicher Spieler angemeldet wurde.
     * 
     * Gibt das Array aller angemeldeten Spieler, wenn sowohl menschliche als
     * auch computergesteuerte Spieler angemeldet wurden.
     * 
     * @param player
     *            Liste vom Typ {@link Player} für Merge-Operation
     * @param hPlayers
     *            Array mit menschlichen Spielern
     * @param aiPlayers
     *            Array mit computergesteuerten Spielern
     * @return Array aller angemeldeten Spieler
     */
    private static Player[] mergePlayerTypes(HumanPlayer[] hPlayers,
	    AIPlayer[] aiPlayers) {
	final int hPlayerCount = hPlayers.length;
	final int aiPlayerCount = aiPlayers.length;
	if (hPlayerCount == 0) {
	    return aiPlayers;
	}
	if (aiPlayerCount == 0) {
	    return hPlayers;
	}
	List<Player> player = new ArrayList<Player>(hPlayers.length
		+ aiPlayers.length);
	Collections.addAll(player, hPlayers);
	Collections.addAll(player, aiPlayers);
	return player.toArray(new Player[player.size()]);
    }

    /**
     * Private Methode zum Ermitteln der gewuenschten Anzahl computergesteuerter
     * Spieler vom Typ {@link AIPlayer}. Wird nur innerhalb dieser Klasse
     * aufgerufen von {@link #mergePlayerTypes(HumanPlayer[], AIPlayer[])}.
     * Überprüft auch, ob versucht wird, mehr als 4 Computergegner zu erstellen.
     * Erstellt automatisch Bezeichnungen
     * 
     * @param name
     *            für die computergesteuerten Spieler.
     * 
     * @return Array aller angemeldeten menschlichen Spieler
     */
    private static AIPlayer[] createAIP() {
	Scanner eingabe = new Scanner(System.in);
	Scanner eingabeString = new Scanner(System.in);
	System.out.print("Wie viele Computergegner sollen erstellt werden? ");
	int aipCount = eingabe.nextInt();
	if (aipCount <= 4) {
	    AIPlayer[] aiplayers = new AIPlayer[aipCount];
	    int startpoint = 0;
	    int endpoint = 52;
	    AtomicLong idCounter = new AtomicLong();

	    for (int i = 0; i < aipCount; i++) {
		String name = "CPU_" + String.valueOf(idCounter.getAndIncrement());
		System.out.printf("Name des %d. Computergegners: " + name, i + 1);
		System.out.println();
		AIPlayer aiplayer = new AIPlayer(startpoint, endpoint, ((char) (i + 35)), name);
		startpoint += 10;
		if (i == 0) {
		    endpoint = 40;
		} else {
		    endpoint += 4;
		}
		aiplayers[i] = aiplayer;
	    }
	    System.out.println();
	    return aiplayers;
	} else
	    System.out.println("Es koennen maximal 4 Computerspieler erstellt werden!");
	return createAIP();
    }

    /**
     * Private Methode zum Ermitteln der gewuenschten Anzahl menschlicher
     * Spieler vom Typ {@link #HumanPlayer}. Wird nur innerhalb dieser Klasse
     * aufgerufen von {@link #mergePlayerTypes(HumanPlayer[], AIPlayer[])}.
     * Überprüft auch, ob versucht wird, mehr als 4 Spieler zu erstellen.
     * 
     * @return Array aller angemeldeten menschlichen Spieler
     */
    private static HumanPlayer[] createHP() {
	Scanner eingabe = new Scanner(System.in);
	Scanner eingabeString = new Scanner(System.in);
	System.out.println();
	System.out.print("Wie viele Spieler spielen mit? ");
	int hpCount = eingabe.nextInt();
	if (hpCount <= 4) {
	    HumanPlayer[] hPlayers = new HumanPlayer[hpCount];
	    int startpoint = 0;
	    int endpoint = 52;
	    for (int i = 0; i < hpCount; i++) {
		System.out.printf("Name des %d. Spielers: ", i + 1);
		String name = eingabeString.nextLine();
		HumanPlayer hPlayer = new HumanPlayer(startpoint, endpoint, ((char) (i + 35)), name);
		startpoint += 10;
		if (i == 0) {
		    endpoint = 40;
		} else {
		    endpoint += 4;
		}
		hPlayers[i] = hPlayer;
	    }
	    System.out.println();
	    return hPlayers;
	} else
	    System.out.println("Es koennen maximal 4 Computerspieler erstellt werden!");
	return createHP();
    }

    public static void noMoves() {
	Scanner eingabe = new Scanner(System.in);
	System.out.println();
	System.out.println("Keine Zuege moeglich! (Mit Enter bestaetigen)");
	eingabe.nextLine();
    }

    public static int printOptions() {
	Scanner eingabe = new Scanner(System.in);
	System.out.println("Welche Aktion moechten Sie durchfuehren?");
	System.out.println("1. Spielen");
	System.out.println("2. Regeln anzeigen");
	System.out.println("3. Spiel beenden");

	while (true) {
	    int number = eingabe.nextInt();
	    // TODO: Buchstaben abfangen
	    if (number < 1 || number > 3) {
		System.out.println("Bitte geben Sie eine Zahl zwischen 1 und 3 ein.");
	    } else {
		System.out.println("");
		return number;
	    }
	}
    }

    /**
     * Methode zur Konsolenausgabe der Spielregeln. Verweist auf
     * {@link DefinedGameRulesPrinter #printOutRules()}. Alle Spielregeln sind
     * in {@link DefinedGameRulesPrinter} ausgelagert, um einen möglichen
     * Austausch der Regeln zu vereinfachen.
     */
    public static void printRules() {
	DefinedGameRulesPrinter.printOutRules();
    }
}
