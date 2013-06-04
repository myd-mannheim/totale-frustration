package hsma.ss2013.oot.groupproject.player;

public class Factory {

    //Singleton
    static Factory factory = new Factory();

    public static MainMenu createGame(){
	return new Game();
    }
    
}
