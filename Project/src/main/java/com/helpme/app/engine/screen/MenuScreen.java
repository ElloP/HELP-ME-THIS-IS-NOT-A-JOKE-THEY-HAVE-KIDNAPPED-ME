package com.helpme.app.engine.screen;

/**
 * Created by kopa on 2017-04-17.
 */
public class MenuScreen extends Screen {

    @Override
    public void update() {

    }

    @Override
    public void render() {

    }

    @Override
    public void input() {

    }

    public void newGame(){
        //TODO
    }

    public void continueGame(){
        //TODO
    }

    public void quitGame(){
        setChanged();
        notifyObservers();
        //TODO
    }

}
