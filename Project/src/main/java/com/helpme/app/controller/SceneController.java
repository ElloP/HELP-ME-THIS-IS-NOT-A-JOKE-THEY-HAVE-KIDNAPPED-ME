package com.helpme.app.controller;

import com.helpme.app.engine.base.EngineCore;
import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.game.scenes.LevelScene;
import com.helpme.app.engine.game.scenes.Menu;
import com.helpme.app.engine.game.scenes.MenuEvent;
import com.helpme.app.saveload.SaveLoad;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.concrete.Body;
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
                //TODO (Jesper): Do the same as for load level but with a new level
                LevelScene newLevelScene = new LevelScene(setup.getLevel(), setup.getPlayer().readBody(), engineCore.getTime());
                game.setActiveScene(newLevelScene);
                playerController = new PlayerController(newLevelScene.getCameraController(), setup.getPlayer(), setup.getLevel());
                newLevelScene.getCameraController().addObserver(this);
                newLevelScene.getCameraController().addObserver(playerController);
                level.readPlayer().run(b -> ((Body) b).addObserver(playerController));
            } else if (((Menu) o).getCurrent() == 0) {
                switchToLevelScene("test.xml");
            } else if (arg == MenuEvent.ESC) {

            }
        }
    }

    private void switchToLevelScene(String filePath) {
        Tuple3<ILevel,Player,IConsciousness[]> loadGame = gameLoader.loadGame(filePath);
        LevelScene levelScene = new LevelScene(loadGame.a, loadGame.b.readBody(), engineCore.getTime());
        game.setActiveScene(levelScene);
        playerController = new PlayerController(levelScene.getCameraController(), loadGame.b, loadGame.a);
        levelScene.getCameraController().addObserver(this);
        loadGame.a.readPlayer().run(b -> ((Body) b).addObserver(playerController));
        levelScene.getCameraController().addObserver(playerController);
    }
}
