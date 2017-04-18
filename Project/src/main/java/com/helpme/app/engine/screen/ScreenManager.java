package com.helpme.app.engine.screen;

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
        changeScreen(currentScreenIndex);
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
        try {
            ScreenType type = (ScreenType) arg;
            switch (type) {
                case MAIMMENU:
                    changeScreen(0);
                    break;
                case GAME:
                    changeScreen(1);
                    break;
                case QUIT:
                    quit = true;
                    break;
            }
        }
        catch (ClassCastException e){
            System.out.println(e);
        }
    }

    private void changeScreen(int next) {
        int nextScreen = Math.floorMod(next, screens.length);
        screens[currentScreenIndex].deleteObserver(this);
        screens[nextScreen].addObserver(this);
        currentScreenIndex = nextScreen;
    }


}
