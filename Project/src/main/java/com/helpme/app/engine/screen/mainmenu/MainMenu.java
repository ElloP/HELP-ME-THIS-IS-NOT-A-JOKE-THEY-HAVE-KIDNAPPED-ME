package com.helpme.app.engine.screen.mainmenu;

import com.helpme.app.engine.screen.Screen;
import com.helpme.app.engine.screen.world.World;

/**
 * Created by kopa on 2017-04-17.
 */
public class MainMenu extends Screen {
    public MainMenu() {
        super(MainMenu.class.getName());
    }

    public void newGame(){
        setChanged();
        notifyObservers(World.class.getName());
        //TODO Setup new game
    }

    public void continueGame(){
        setChanged();
        notifyObservers(World.class.getName());
        //TODO Load game
    }

    public void quitGame(){
        setChanged();
        notifyObservers(null);
        //TODO
    }

}
