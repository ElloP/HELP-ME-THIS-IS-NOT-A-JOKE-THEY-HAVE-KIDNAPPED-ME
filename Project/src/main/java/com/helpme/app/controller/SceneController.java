package com.helpme.app.controller;

import com.helpme.app.Setup;
import com.helpme.app.engine.base.EngineCore;
import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.game.*;
import com.helpme.app.engine.game.scenes.LevelScene;
import com.helpme.app.engine.game.scenes.Menu;
import com.helpme.app.engine.game.scenes.MenuEvent;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.saveload.SaveLoad;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.concrete.Player;
import com.helpme.app.world.level.ILevel;

import java.util.Observable;

/**
 * Created by Jesper on 2017-05-22.
 */
public class SceneController implements IController {
    private Game game;
    private SaveLoad gameLoader;
    private IController playerController;
    private EngineCore engineCore;

    public SceneController(Game game, SaveLoad gameLoader, EngineCore engineCore) {
        this.game = game;
        this.gameLoader = gameLoader;
        this.engineCore = engineCore;
        Scene menu = new Menu();
        this.game.setActiveScene(menu);
        menu.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Menu) {
            if (((Menu) o).getCurrent() == 1) {
                //Tuple3<ILevel,Player,IConsciousness[]> newGame = gameLoader.loadGame("");
                Setup setup = new Setup();
                ILevel level = setup.setup();
                //TODO (Jesper): Add all constructor arguments
                LevelScene newLevelScene = new LevelScene(setup.getLevel(), setup.getPlayer().readBody(), engineCore.getTime());
                game.setActiveScene(newLevelScene);
                playerController = new PlayerController(newLevelScene.getCameraController(), setup.getPlayer(), setup.getLevel());
                addObservers(newLevelScene);
            } else if (((Menu) o).getCurrent() == 0) {
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
