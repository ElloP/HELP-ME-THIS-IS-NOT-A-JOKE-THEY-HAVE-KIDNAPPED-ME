package com.helpme.app.engine.screen;

import com.helpme.app.utils.Tuple.Tuple2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kopa on 2017-04-17.
 */


public class ScreenManager extends Screen implements IScreenManager {
    private IScreen[] screens;
    private boolean quit;

    public ScreenManager(IScreen[] screens) {
        super(ScreenManager.class.getName(), screens);
    }

    public boolean isQuit() {
        return quit;
    }

    @Override
    public void update() {
        IScreen screen = getCurrentScreen();
        screen.input();
        screen.update();
        screen.render();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg == null){
            setQuit(true);
            return;
        }
        try {
            String name = (String) arg;
            tryChangeScreen(name);
        } catch (ClassCastException e) {
            System.out.println(e);
        }
    }

    private void setQuit(boolean value){
        quit = value;
    }

}
