package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.*;

/**
 * Authored by Olle on 2017-04-21.
 */
public class GameInstance extends Game {

    @Override
    public void input(Time time) {
        scene.input(time);
    }

    public void update(Time time) {
        //TODO(Olle): update game

    }
}
