package hsma.ss2013.oot.groupproject.board;

import hsma.ss2013.oot.groupproject.game.Move;
import hsma.ss2013.oot.groupproject.game.MoveType;
import hsma.ss2013.oot.groupproject.player.Player;

import java.util.ArrayList;

public class Board {
    public Field[][] field;
    Player[] players;

    public Board(Player[] players) {
	this.players = players;
	field = new Field[40][1];
	field[9] = new Field[5];
	field[19] = new Field[5];
	field[29] = new Field[5];
	field[39] = new Field[5];
	init();
    }

    /**
     * Initialisiert das Spielfeld
     */
    private void init() {
	int index = 0;
	// Erstelle Felder
	for (int i = 0; i < field.length; i++) {
	    field[i][0] = new Field(index);
	    index++;
	}
	// Erstelle Spieler Hausfelder
	int playerNumber = 1;
	for (int j = 9; j <= 39; j += 10) {
	    Player currentPlayer = null;
	    if (playerNumber < players.length) {
		currentPlayer = players[playerNumber];
	    }
	    for (int i = 1; i < 5; i++) {
		field[j][i] = new House(index, currentPlayer);
		index++;
	    }
	    if (playerNumber == 3) {
		playerNumber = -1;
	    }
	    playerNumber++;
	}
	setCoords();
    }

    /**
     * Gibt das Spielfeld-Array {@code #field[][]} zurueck
     * 
     * @return
     */
    public Field[][] getFieldArray() {
	return field.clone();
    }

    /**
     * Gibt das Spielfeld an der Stelle {@code index} zurueck
     * 
     * @param index
     * @return
     */
    public Field getField(int index) {
	if (index > 55) {
	    System.out.println("Gr��er als Spielfeld");
	    return null;
	}
	if (index > 39) {
	    int nIndex = index - 39;
	    int row = 9;
	    int offset = nIndex / 4;
	    while (offset > 0) {
		row += 10;
		offset--;
	    }
	    if (nIndex % 4 == 0) {
		return field[row][0];
	    } else {
		return field[row][nIndex % 4];
	    }
	} else {
	    return field[index][0];
	}
    }

    /**
     * Gibt alle angemeldeten Spieler in einem Array {@code Player[]} zurueck
     * 
     * @return
     */
    public Player[] getPlayers() {
	return players;
    }

    /**
     * Wertet die Art des Zuges aus und daran an die entsprechende ausf�hrende
     * Methode
     * 
     * @param move
     *            - Object das Informationen zum Zug bereith�lt
     * @param diceRoll
     *            - gew�rfelte Zahl
     */
    public void moveToken(Move move, int diceRoll) {
	
	switch (move.getMoveType()) {
	// TODO
	case THROW:
	    throwToken(move, diceRoll);
	    break;
	case FINISH:
	    moveHome(move, diceRoll);
	    break;
	case BARRIER:
	    buildBarrier(move, diceRoll);
	    break;
	case MOVE:
	    simpleMoveToken(move, diceRoll);
	    break;
	case START:
	    moveToStart(move);
	    break;
	case START_THROW:
	    System.out.println("FEHLER!!!");
	    moveToStartAndThrow(move);
	    break;
	case SUSPEND:
	    break;

	}

    }

    

    /**
     * Versetzt eine Figur um die gew�rfelten Schritte und schmei�t gegenerische
     * Figuren zur�ck in deren Homefield
     * 
     * @param move
     *            - MoveObject das Informationen zum Zug tr�gt
     * @param diceRoll
     *            - gew�rfelte Zahl
     */
    private void throwToken(Move move, int diceRoll) {
	Token attackingToken = move.getToken();

	int targetField = 0;
	// WENN �BER 39 GEZOGEN WIRD MUSS ZUR�CK AUF 1 gegangen werden
	if (attackingToken.getPosition() + diceRoll < 40) {
	    targetField = attackingToken.getPosition() + diceRoll;
	} else {
	    targetField = ((attackingToken.getPosition() + diceRoll) % 40);
	}

	// Liste mit Tokens auf dem Zielfeld
	ArrayList<Token> tokensToThrow = this.field[targetField][0].getToken();
	Token targetToken = tokensToThrow.get(0);
	targetToken.moveTo(-1);
	tokensToThrow.remove(targetToken);
	targetToken.setMovesToNull();
	targetToken.getOwner().moveIntoStart(targetToken);

	// Schlagende Figur aufs Spielfeld setzen und Schrittecounter erh�hen
	simpleMoveToken(move, diceRoll);
    }

    /**
     * L�uft mit einer Figur bis deren Homefeld. Mit den �brigen Schritten
     * l�ufft die Figur in ihr Homefeld hinein.
     * 
     * @param move
     *            - MoveObject das Informationen zum Zug tr�gt
     * @param diceRoll
     *            - gew�rfelte Zahl
     */
    private void moveHome(Move move, int diceRoll) {
	Token currentToken = move.getToken();
	// Restschritte die im Homefeld gelaufen werden ermitteln
	int restSteps = diceRoll - currentToken.getRestSteps();

	// Position des Homefelds ermitteln
	int homeFieldPos = currentToken.getOwner().getEndpoint();
	// Schrittcounter hochsetzen (Vorsicht z�hlt nur bis vor das Homefield)
	currentToken.addMoves(currentToken.getRestSteps());

	// Token von alter Position entfernen
	this.field[currentToken.getPosition()][0].deleteToken(currentToken);

	// Token ins Homefield setzen
	this.field[homeFieldPos][restSteps].setToken(currentToken);
	currentToken.addMoves(diceRoll);

    }

    /**
     * L�uft mit der Figur die gew�rfelten Schritte ab und baut am Ziel eine
     * Barriere
     * 
     * @param move
     *            - MoveObject das Informationen zum Zug tr�gt
     */
    private void buildBarrier(Move move, int diceRoll) {
	simpleMoveToken(move, diceRoll);
    }

    /**
     * Methode fuer normalen Zug einer Spielfigur
     * 
     * @param move
     * @param diceRoll
     */
    private void simpleMoveToken(Move move, int diceRoll) {
	int tokenPosition = move.getToken().getPosition();

	ArrayList<Token> tokens = getField(tokenPosition).getToken();

	Token tokenToMove = tokens.get(findToken(tokens, move.getToken()));

	tokens.remove(findToken(tokens, move.getToken()));

	getField(tokenPosition).toggleBarrier();
	int destiny = 0;
	// WENN �BER 39 GEZOGEN WIRD MUSS ZUR�CK AUF 1 gegangen werden
	if (tokenPosition + diceRoll <= 39) {
	    destiny = tokenPosition + diceRoll;
	} else {
	    destiny = ((tokenPosition + diceRoll) % 39) - 1;
	}
	Field destination = getField(destiny);
	destination.setToken(tokenToMove);
	destination.toggleBarrier();
	tokenToMove.moves += diceRoll;
	tokenToMove.moveTo(destiny);

    }

    private int findToken(ArrayList<Token> tokens, Token token) {
	if (tokens.get(0) == token) {
	    return 0;
	} else {
	    return 1;
	}

    }

    /**
     * Methode, um eine Spielfigur auf das Startfeld zu setzen
     * 
     * @param move
     */
    private void moveToStart(Move move) {
	Field startField = this.getField(move.getOwner().getStartpoint());
	ArrayList<Token> tokenHome = move.getOwner().getHome();
	Token tokenToMove = tokenHome.get(0);
	tokenHome.remove(0);
	/*if (!startField.getToken().isEmpty()) {
	    startField.getToken().get(0).setMovesToNull();
	    startField.getToken().get(0).moveTo(-1);
	    startField.setToken(tokenToMove);
	    
	} else {
	    startField.setToken(tokenToMove);
	}*/
	startField.setToken(tokenToMove);
	tokenToMove.moves++;
	tokenToMove.moveTo(startField.getIndex());
    }
    
    private void moveToStartAndThrow(Move move) {
	Field startField = this.getField(move.getOwner().getStartpoint());
	Token tokenToThrow = startField.getToken().get(0);
	//Move throwMove = new Move(tokenToThrow.getOwner(), tokenToThrow, MoveType.THROW);
	tokenToThrow.moveTo(-1);
	startField.getToken().remove(tokenToThrow);
	tokenToThrow.setMovesToNull();
	tokenToThrow.getOwner().moveIntoStart(tokenToThrow);
	
	this.moveToStart(move);
	
    }

    /**
     * Methode zur Ueberpruefung des Hauses eines Spielers
     * 
     * @return
     */
    public boolean isFullHouse() {

	for (int i = 9; i <= 39; i += 10) {

	    if ((!field[i][1].isEmpty()) && (!field[i][2].isEmpty())
		    && (!field[i][3].isEmpty()) && (!field[i][4].isEmpty())) {
		System.out.println();
		System.out.println("Spieler: "
			+ field[i][1].getToken().get(0).getOwner()
			+ " hat gewonnen!");
		return true;
	    }
	}

	return false;
    }

    /**
     * Setzt die Koordinaten zur Ausgabe auf das Spielfeld
     */
    private void setCoords() {
	this.field[0][0].setFieldKoord(0, 4);
	this.field[1][0].setFieldKoord(1, 4);
	this.field[2][0].setFieldKoord(2, 4);
	this.field[3][0].setFieldKoord(3, 4);
	this.field[4][0].setFieldKoord(4, 4);

	this.field[5][0].setFieldKoord(4, 3);
	this.field[6][0].setFieldKoord(4, 2);
	this.field[7][0].setFieldKoord(4, 1);
	this.field[8][0].setFieldKoord(4, 0);

	this.field[9][0].setFieldKoord(5, 0);
	// Zielfelder Spieler 2
	this.field[9][1].setFieldKoord(5, 1);
	this.field[9][2].setFieldKoord(5, 2);
	this.field[9][3].setFieldKoord(5, 3);
	this.field[9][4].setFieldKoord(5, 4);

	this.field[10][0].setFieldKoord(6, 0);
	this.field[11][0].setFieldKoord(6, 1);
	this.field[12][0].setFieldKoord(6, 2);
	this.field[13][0].setFieldKoord(6, 3);
	this.field[14][0].setFieldKoord(6, 4);

	this.field[15][0].setFieldKoord(7, 4);
	this.field[16][0].setFieldKoord(8, 4);
	this.field[17][0].setFieldKoord(9, 4);
	this.field[18][0].setFieldKoord(10, 4);

	this.field[19][0].setFieldKoord(10, 5);
	// Zielfelder Spieler 3
	this.field[19][1].setFieldKoord(9, 5);
	this.field[19][2].setFieldKoord(8, 5);
	this.field[19][3].setFieldKoord(7, 5);
	this.field[19][4].setFieldKoord(6, 5);

	this.field[20][0].setFieldKoord(10, 6);
	this.field[21][0].setFieldKoord(9, 6);
	this.field[22][0].setFieldKoord(8, 6);
	this.field[23][0].setFieldKoord(7, 6);
	this.field[24][0].setFieldKoord(6, 6);

	this.field[25][0].setFieldKoord(6, 7);
	this.field[26][0].setFieldKoord(6, 8);
	this.field[27][0].setFieldKoord(6, 9);
	this.field[28][0].setFieldKoord(6, 10);

	this.field[29][0].setFieldKoord(5, 10);
	// Zielfelder Spieler 4
	this.field[29][1].setFieldKoord(5, 9);
	this.field[29][2].setFieldKoord(5, 8);
	this.field[29][3].setFieldKoord(5, 7);
	this.field[29][4].setFieldKoord(5, 6);

	this.field[30][0].setFieldKoord(4, 10);
	this.field[31][0].setFieldKoord(4, 9);
	this.field[32][0].setFieldKoord(4, 8);
	this.field[33][0].setFieldKoord(4, 7);
	this.field[34][0].setFieldKoord(4, 6);

	this.field[35][0].setFieldKoord(3, 6);
	this.field[36][0].setFieldKoord(2, 6);
	this.field[37][0].setFieldKoord(1, 6);
	this.field[38][0].setFieldKoord(0, 6);

	this.field[39][0].setFieldKoord(0, 5);
	// Zielfelder Spieler 1
	this.field[39][1].setFieldKoord(1, 5);
	this.field[39][2].setFieldKoord(2, 5);
	this.field[39][3].setFieldKoord(3, 5);
	this.field[39][4].setFieldKoord(4, 5);

    }
}
