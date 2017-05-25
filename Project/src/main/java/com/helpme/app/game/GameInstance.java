package com.helpme.app.game;

import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Time;

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
