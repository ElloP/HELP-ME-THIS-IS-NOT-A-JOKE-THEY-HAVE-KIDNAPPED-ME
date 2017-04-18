package com.helpme.app.engine.screen;

import com.helpme.app.utils.Tuple.Tuple2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kopa on 2017-04-17.
 */


public class ScreenManager implements Observer {
    private IScreen[] screens;
    private int currentScreenIndex;
    private boolean quit;

    public ScreenManager(IScreen[] screens, int currentScreenIndex) {
        this.screens = screens;
        this.currentScreenIndex = currentScreenIndex;
        screens[this.currentScreenIndex].addObserver(this);
    }

    public boolean isQuit() {
        return quit;
    }

    public void update() {
        IScreen screen = screens[currentScreenIndex];
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
            changeScreen(name);
        } catch (ClassCastException e) {
            System.out.println(e);
        }
    }

    private void setQuit(boolean value){
        quit = value;
    }

    private void changeScreen(String name) {
        for(int i = 0; i < screens.length; i++){
            if(screens[i].getName() == name){
                screens[currentScreenIndex].deleteObserver(this);
                screens[i].addObserver(this);
                currentScreenIndex = i;
                return;
            }
        }
    }

}
