package hsma.ss2013.oot.groupproject.player_old;

public class Factory {
	//TODO
	//"Factory" scheint mir kein geschickter name zu sein: Bitte lieber z.B. GameFactory falls sie Spiele zur�ck gibt
	//solange der Konstruktor nicht private ist, ist die Klasse kein Singleton (warum muss sie �berhaupt singelton sein?)
	//vlt. lieber public static Game getGame(...) 

    //Singleton
    static Factory factory = new Factory();

    //public static MainMenu createGame(){
	//return new Game();
   // }
    
}
