package com.helpme.app.game.controller;

import com.helpme.app.engine.base.EngineCore;
import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.saveload.SaveLoad;
import com.helpme.app.utils.tuple.Tuple3;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-22.
 */
public class SceneController implements Observer {
    private Game game;
    private SaveLoad gameLoader;
    private Observer playerController;
    private EngineCore engineCore;

    public SceneController(Game game, SaveLoad gameLoader, EngineCore engineCore) {
        this.game = game;
        this.gameLoader = gameLoader;
        this.engineCore = engineCore;
        Scene menu = new MenuController();
        this.game.setActiveScene(menu);
        menu.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MenuController) {
            if (((MenuController) o).getCurrent() == 1) {
                startGame(loadState("level1.xml"));
            } else if (((MenuController) o).getCurrent() == 0) {
                startGame(loadState("test.xml"));
            } else if (arg == MenuEvent.ESC) {

            }
        }
    }

    private Tuple3<ILevel,Player,IConsciousness[]> loadState(String filepath){
        return gameLoader.loadGame(filepath);
    }

    private void startGame(Tuple3<ILevel,Player,IConsciousness[]> loadedState) {
        LevelController levelScene = new LevelController(loadedState.a, loadedState.b.readBody(), engineCore.getTime());
        game.setActiveScene(levelScene);
        playerController = new PlayerController(levelScene.getCameraController(), loadedState.b, loadedState.a);
        levelScene.getCameraController().addObserver(this);
        loadedState.a.readPlayer().run(b ->  b.addObserver(playerController));
        levelScene.getCameraController().addObserver(playerController);
        addAudioObserver(loadedState.b.readBody(), loadedState.a.readBodies());
    }

    private void addAudioObserver(IReadBody playerBody, List<IReadBody> enemies) {
        Observer levelAudioController = AudioSetup.setupAudioController(playerBody, enemies);
        for (IReadBody body : enemies) {
            body.addObserver(levelAudioController);
        }
        playerBody.addObserver(levelAudioController);

    }
}
