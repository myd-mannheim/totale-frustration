package hsma.ss2013.oot.groupproject.test;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Diese JUnit Klasse enthaelt Tests für den Wuerfel({@link Dice}). </br>
 * Jeder Testfall enthaelt die Anzahl der wuerfe und ein delta, das 
 * die erlaubte Abweichung repraesentiert.(Die Abweichung wird nie 0.0 % 
 * betragen, aber gegen sie streben.)
 * @author Bartosz
 *
 */
public class DiceTest {

	
	@Test
	public void test600000() {
		
		double delta = 0.15;
		
		double anzahl = 600000.0;
		
		assertEquals(0.0, DiceResult.getResult(anzahl), delta);
		
	}
	
	@Test
	public void test6000000() {
		
		double delta = 0.05;
		
		double anzahl = 6000000.0;
		
		assertEquals(0.0, DiceResult.getResult(anzahl), delta);
		
	}
	
	@Test
	public void test60000000() {
		
		double delta = 0.01;
		
		double anzahl = 60000000.0;
		
		assertEquals(0.0, DiceResult.getResult(anzahl), delta);
		
	}

}
