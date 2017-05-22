package com.helpme.app.controller;

import com.helpme.app.engine.game.GameInstance;
import com.helpme.app.engine.game.LevelScene;
import com.helpme.app.engine.game.Menu;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.level.ILevel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-22.
 */
public class SceneController implements Observer {
    private GameInstance gameInstance;
    private GameLoader gameLoader;

    public SceneController(GameInstance gameInstance, GameLoader gameLoader) {
        this.gameInstance = gameInstance;
        this.gameLoader = gameLoader;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Menu) {
            if (((Menu) o).getCurrent() == 0) {
                Tuple3<ILevel,IBody,IConsciousness[]> newGame = gameLoader.loadGame("");
                //TODO (Jesper): Add all constructor arguments
                gameInstance.setActiveScene(new LevelScene(newGame.a));
            } else if (((Menu) o).getCurrent() == 1) {

            }
            //TODO (Jesper): Should add case for when user presses ESC
        }
    }
}
