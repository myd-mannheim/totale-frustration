package hsma.ss2013.oot.groupproject.player_old;

public class AIPlayer {

    private Piece piece;

    /**
     * @return the piece
     */
    public Piece getPiece() {
    	
    	/*
    	
    	//Pseudo Code f�r AI Spielzug ermittlung
    	def moveRatings[4]
    			
    	def moveRating1[x]
    	def moveRating2[x]
    	def moveRating3[x]
    	def moveRating4[x]
    	
    	moveRatings[1] = moveRating1;
    	moveRatings[2] = moveRating2;
    	moveRatings[3] = moveRating3;
    	moveRatings[4] = moveRating4;
    	
    	//F�lle f�r jeder Spielfigur das Zugbewertungs Array    	
    	for each Spielfigur into figur
    		def moves[] = game->getPossibleMoves(figur);
    		for each moves into move
    			doMoveRating(move, moveRatings[figur.number])
    		rof
    	rof
    	
    	//jetzt muss der h�chst priorisierteste Zug gefunden werden der auf true sitz
    	//�beralle Spiefiguren
    	 * 
    	 * 
    	 */
    	
    	
	//TODO Implementierung
	return piece;
    }

    /**
     * @param piece
     *            the piece to set
     */
    @SuppressWarnings("unused")
    private void setPiece(Piece piece) {
	this.piece = piece;
    }
}
