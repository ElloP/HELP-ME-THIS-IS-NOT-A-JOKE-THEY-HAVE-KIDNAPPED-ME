package com.helpme.app.engine.screen;

import com.helpme.app.utils.Tuple.Tuple2;
import javafx.beans.InvalidationListener;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kopa on 2017-04-18.
 */
public class Screen extends Observable implements IScreen, Observer {
    private IScreen[] screens;
    private int activeScreenIndex;
    private boolean enabled;
    private String name;

    public Screen(String name) {
        this(name, true, null);
    }

    public Screen(String name, IScreen[] screens) {
        this(name, true, screens);
    }

    public Screen(String name, boolean enabled, IScreen[] screens) {
        this.name = name;
        this.enabled = enabled;
        this.screens = screens;

        for(IScreen screen : screens){
            screen.addObserver(this);
        }
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        IScreen screen = (IScreen) o;
        return this.name == screen.getName();
    }

    @Override
    public void update() {
        for (IScreen screen : screens) {
            if (screen.isEnabled()) {
                screen.update();
            }
        }
    }

    @Override
    public void render() {
        for (IScreen screen : screens) {
            if (screen.isEnabled()) {
                screen.render();
            }
        }
    }

    @Override
    public void input() {
        screens[activeScreenIndex].input();
    }

    @Override
    public void delete() {
        for (IScreen screen : screens) {
            screen.delete();
        }
    }

    @Override
    public void start() {
        for (IScreen screen : screens) {
            screen.start();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            notifyQuit();
            return;
        }

        try {
            String name = (String) arg;
            if (!tryChangeScreen(name)) {
                notifyChangeScreen(name);
            }
        } catch (ClassCastException e) {
            System.out.println(e);
        }
    }

    @Override
    public void setEnabled(boolean value) {
        enabled = value;
    }

    protected IScreen getCurrentScreen(){
        return screens[activeScreenIndex];
    }

    protected boolean tryChangeScreen(String name) {
        for (int i = screens.length; i >= 0; i--) {
            if (screens[i].getName() == name) {
                screens[i].setEnabled(true);
                activeScreenIndex = i;
                return true;
            }
        }

        return false;
    }

    private void notifyChangeScreen(String name) {
        setChanged();
        notifyObservers(name);
    }

    private void notifyQuit() {
        setChanged();
        notifyObservers(null);
    }
}
