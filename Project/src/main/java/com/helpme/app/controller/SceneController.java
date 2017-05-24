package com.helpme.app.controller;

import com.helpme.app.engine.base.EngineCore;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.game.*;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.concrete.Player;
import com.helpme.app.world.level.ILevel;

import java.util.Observable;

/**
 * Created by Jesper on 2017-05-22.
 */
public class SceneController implements IController {
    private GameInstance gameInstance;
    private GameLoader gameLoader;
    private IController playerController;
    private EngineCore engineCore;

    public SceneController(GameInstance gameInstance, GameLoader gameLoader, IController playerController, EngineCore engineCore) {
        this.gameInstance = gameInstance;
        this.gameLoader = gameLoader;
        this.engineCore = engineCore;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Menu) {
            if (arg == MenuEvent.NEW) {
                Tuple3<ILevel,Player,IConsciousness[]> newGame = gameLoader.loadGame("");
                //TODO (Jesper): Add all constructor arguments
                LevelScene newLevelScene = new LevelScene(newGame.a, newGame.b.readBody(), engineCore.getTime());
                gameInstance.setActiveScene(newLevelScene);
                playerController = new PlayerController(newLevelScene.getCameraController(), newGame.b, newGame.a);
                addObservers(newLevelScene);
            } else if (arg == MenuEvent.LOAD) {
                Tuple3<ILevel,Player,IConsciousness[]> loadGame = gameLoader.loadGame("test.xml");
                Scene loadLevelScene = new LevelScene(loadGame.a, loadGame.b.readBody(), engineCore.getTime());
                addObservers(loadLevelScene);
            } else if (arg == MenuEvent.ESC) {

            }
        }
    }

    public void addObservers(Scene scene) {
        scene.addObserver(this);
    }
}
