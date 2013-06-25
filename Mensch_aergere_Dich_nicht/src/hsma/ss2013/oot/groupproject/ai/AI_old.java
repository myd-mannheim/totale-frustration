package hsma.ss2013.oot.groupproject.ai;

public class AI_old {
    /*
	 * Token lastToken = moveArray[0].getToken(); 
	 * int tokenRatingCounter = -1; 
	 * 
	 * Führe für jeden Token die Bewertung durch (setzten des
	 * entsprechenden Arrayplatzes in rating[][] 
	 * 
	 * for (int i = 0; i < moveArray.length; i++) { 
	 *	if (!(lastToken.getID() == moveArray[i].getToken().getID())) { 
	 * 	lastToken = moveArray[i].getToken(); 
	 * 	tokenRatingCounter++;
	 * }
	 */
	/*
	 * Setzt die entsprechenden Arrayplätze für jedes Token in {@code
	 * rating[]} in der Reihenfolge des Arrays {@code
	 * moveTypeDefaultOrder[]} auf true (= Spielzug möglich) oder false (=
	 * Spielzug nicht möglich). Wird für die spätere Auswertung /
	 * Priorisierung des {@link AIPlayer} verwendet.
	 */
	/*
	 * switch (moveArray[i].getMoveType()) { 
	 * case THROW:
	 * 	this.rating[tokenRatingCounter][0] = true; break; 
	 * case FINISH:
	 * 	this.rating[tokenRatingCounter][1] = true; break; 
	 * case START:
	 * 	this.rating[tokenRatingCounter][2] = true; break; 
	 * case MOVE:
	 * 	this.rating[tokenRatingCounter][3] = true; break; 
	 * case SUSPEND:
	 * 	this.rating[tokenRatingCounter][4] = true; break; 
	 * default: break; } }
	 * 	System.out.println(moveArray[i].getToken().getID());
	 * 
	 * }
	 */
}
