package hsma.ss2013.oot.groupproject.game;

import hsma.ss2013.oot.groupproject.interfaces.MainMenu;

public class GameFactory {

    public static MainMenu getGame(){
	return new Game();
    }

}
