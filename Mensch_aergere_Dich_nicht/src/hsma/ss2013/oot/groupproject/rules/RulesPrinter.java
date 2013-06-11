package hsma.ss2013.oot.groupproject.rules;

public abstract class RulesPrinter {

    public static void printOutRules() {
	System.out.println("_______________________________________________________________________________________________");
	System.out.println("Spielregeln:");
	System.out.println("");
	System.out.println("Wer eine Sechs wuerfelt, muss eine eigene Spielfigur aus der Startposition");
	System.out.println("heraus auf sein Startfeld des Spielfeldes stellen. Danach darf er erneut wuerfeln");
	System.out.println("und muss mit seiner Spielfigur der Augenanzahl des Wuerfels entsprechend viele Felder");
	System.out.println("vorruecken und damit das Startfeld freimachen.");
	System.out.println("Hat der Spieler jedoch keine Spielfigur in Startposition, d.h. alle seine Spielfiguren");
	System.out.println("sind im Spiel, kann er frei entscheiden, mit welcher seiner Spielfiguren er die erwuerfelten");
	System.out.println("sechs Spielfelder vorrueckt (auch die letzte Spielfigur, die auf dem Startfeld steht).");
	System.out.println("Auch dann darf der Spieler erneut wuerfeln und einen weiteren Zug machen.");
	System.out.println("Welche Spielfigur der Spieler beim erneuten Zug vorrueckt, bleibt ihm ueberlassen.");
	System.out.println("");
	System.out.println("Kommt beim Umlauf eine Spielfigur auf ein Feld zum Stehen, welches bereits");
	System.out.println("von einer gegnerischen Spielfigur besetzt ist, gilt die gegnerische Spielfigur als");
	System.out.println("geschlagen und muss zurueck ins Haeuschen. Eigene Figuren koennen nicht geschlagen werden.");
	System.out.println("Sind keine weiteren eigenen Spielfiguren im Spiel, kann der Zug somit nicht ausgefuehrt werden,");
	System.out.println("der naechste Spieler ist an der Reihe.");
	System.out.println("Hat ein Spieler mehrere Spielfiguren im Spielumlauf, darf dieser frei entscheiden, ");
	System.out.println("mit welcher Spielfigur er ziehen moechte.");
	System.out.println("Die Anzahl der Augen eines Wurfs duerfen nicht auf mehrere Spielfiguren verteilt werden.");
	System.out.println("");
	System.out.println("_______________________________________________________________________________________________");
	System.out.println("Sonderregeln:");
	System.out.println("");
	System.out.println("1. Hat ein Spieler ueberhaupt keine Spielfigur auf dem Spielfeld (trifft bei Spielbeginn auf");
	System.out.println("alle Spieler zu), so hat er drei Versuche eine Sechs zu wuerfeln, um eine Spielfigur ins Spiel");
	System.out.println("zu bringen.");
	System.out.println("2. Wer mit einer gewuerfelten Zahl eine Spielfigur eines Gegners schlagen kann, muss dies");
	System.out.println("ausfuehren (Schlagzwang).");
	System.out.println("3. Erreicht eine Spielfigur durch den Zug ein Feld, auf dem bereits eine eigene Figur steht,");
	System.out.println("so bilden diese beiden Figuren eine Barriere. Diese Barriere kann von keiner Spielfigur");
	System.out.println("uebersprungen werden (auch nicht von eigenen Spielfiguren). Die Spielfiguren, die die");
	System.out.println("Barriere bilden, koennen nicht geschlagen werden. Die Barriere bleibt so lange bestehen");
	System.out.println("bis eine Spielfigur weitergezogen wird bzw. werden muss.");
	System.out.println("");
	System.out.println("");
	System.out.println("_______________________________________________________________________________________________");
    }
}
