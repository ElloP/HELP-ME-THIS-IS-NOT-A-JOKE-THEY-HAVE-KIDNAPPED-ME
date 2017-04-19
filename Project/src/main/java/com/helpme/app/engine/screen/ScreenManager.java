package com.helpme.app.engine.screen;

import com.helpme.app.utils.Tuple.Tuple2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by kopa on 2017-04-17.
 */


public class ScreenManager extends Screen {
    public ScreenManager(IScreen[] screens) {
        super(ScreenManager.class.getName(), screens);
    }

    @Override
    public void update() {
        getCurrentScreen().update();
    }

    @Override
    public void input(){
        getCurrentScreen().input();
    }

    @Override
    public void render(){
        getCurrentScreen().render();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg == null){
            setEnabled(false);
            return;
        }
        try {
            String name = (String) arg;
            tryChangeScreen(name);
        } catch (ClassCastException e) {
            System.out.println(e);
        }
    }
}
