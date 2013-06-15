package hsma.ss2013.oot.groupproject.test;

import hsma.ss2013.oot.groupproject.board.Dice;
import hsma.ss2013.oot.groupproject.board.DiceAccess;

/**
 * Diese Klasse bildet ein Teil der Verifikation des Wuerfels ({@link Dice}). </br>
 * Sie erstellt für n Wuerfe einen Mittelwert, der dann mit dem Erwartungswert von 3.5
 * verglichen wird und die Abweichung in % ausgibt. </br>
 * Das Gesetz der großen Zahl gewährleistet, dass sich dieser Wert nach vielen Wiederholungen 
 * ungefähr ergibt. Bei nur sehr wenigen Wiederholungen gibt es aber eine hohe Schwankungsbreite.
 * 
 * @author MichaelWilk
 *
 */
public class DiceResult {
	
	/**
	 * Diese Methode berechnet die Abweichung des Wuerfels ({@link Dice}) vom Erwartungswert.
	 * @param anzahl Anzahl der Wuerfe.
	 * @return Die Abweichung der Berechnung von dem Erwartungswert in %.
	 */
	public static double getResult(double anzahl){
		
		DiceAccess dice = Dice.getDice();
		
		double i = 0;
		
		double a1= 0;
		double a2= 0;
		double a3= 0;
		double a4= 0;
		double a5= 0;
		double a6= 0;
		
		do{
		
		int n = dice.roll();
		
		switch(n) {
		case 1 : a1++; break;
		case 2 : a2++; break;
		case 3 : a3++; break;
		case 4 : a4++; break;
		case 5 : a5++; break;
		case 6 : a6++; break;
		default : 
		}
		
		i++;
		
		}while(i<anzahl);
		
		double ergebniswert = ((a1*1.0)+(a2*2.0)+(a3*3.0)+(a4*4.0)+(a5*5.0)+(a6*6.0))/anzahl;
		
		return (Math.abs((((100.00/3.5) * ergebniswert)) -100));
		
	}
	
}
