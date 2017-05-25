package com.helpme.app.game.controller;

import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.saveload.SaveLoad;
import com.helpme.app.game.view.camera.CameraViewFactory;
import com.helpme.app.game.view.camera.PlayerCameraView;
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
    private PlayerController playerController;
    private Time time;

    public SceneController(Game game, SaveLoad gameLoader, Time time) {
        this.game = game;
        this.gameLoader = gameLoader;
        this.time = time;
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

    private Tuple3<ILevel, Player, IConsciousness[]> loadState(String filepath) {
        return gameLoader.loadGame(filepath);
    }

    private void startGame(Tuple3<ILevel, Player, IConsciousness[]> loadedState) {
        IConsciousness player = loadedState.b;
        ILevel level = loadedState.a;
        PlayerCameraView playerCameraView = CameraViewFactory.createPlayerCameraView(player.readBody(), time);
        playerController = ControllerFactory.createPlayerController(playerCameraView, player);
        Scene levelScene = ControllerFactory.createLevelController(level, playerCameraView, playerController);

        playerCameraView.addObserver(this);
        addAudioObserver(player.readBody(), level.readBodies());
        game.setActiveScene(levelScene);
    }

    private void addAudioObserver(IReadBody playerBody, List<IReadBody> bodies) {
        Observer levelAudioController = ControllerFactory.createLevelAudioController(playerBody, bodies);
        for (IReadBody body : bodies) {
            body.addObserver(levelAudioController);
        }
    }
}
