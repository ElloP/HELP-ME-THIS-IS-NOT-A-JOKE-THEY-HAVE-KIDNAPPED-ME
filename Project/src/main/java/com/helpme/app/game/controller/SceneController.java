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

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-22.
 */

public class SceneController implements Observer {
    private final String START_LEVEL = "level1.xml";
    private final String SAVED_LEVEL = "saved_game.xml";
    private Game game;
    private SaveLoad gameLoader;

    private Tuple3<ILevel, Player, IConsciousness[]> currentState;

    private Time time;

    public SceneController(Game game, SaveLoad gameLoader, Time time) {
        this.game = game;
        this.gameLoader = gameLoader;
        this.time = time;
        Scene menu = new MenuController();
        this.game.setActiveScene(menu);
        menu.addObserver(this);
    }

    public void stop() {
        saveState(SAVED_LEVEL);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MenuController) {
            if (((MenuController) o).getCurrent() == 1) {
                try {
                    startGame(START_LEVEL);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Couldn't load " + START_LEVEL + " in SceneController.update::" + e);
                }
            } else if (((MenuController) o).getCurrent() == 0) {

                try {
                    startGame(SAVED_LEVEL);
                } catch (FileNotFoundException e0) {
                    System.out.println("Couldn't load " + SAVED_LEVEL + " in SceneController.update::" + e0);
                    try {
                        startGame(START_LEVEL);
                    }catch (FileNotFoundException e1){
                        throw new RuntimeException("Couldn't load " + START_LEVEL + " in SceneController.update::" + e1);
                    }
                }
            }

        }
    }

    private void startGame(String filename) throws FileNotFoundException {
        Tuple3<ILevel, Player, IConsciousness[]> state = loadState(filename);
        initGame(state);
    }

    private void saveState(String filepath) {
        gameLoader.saveGame(currentState.a, currentState.b, currentState.c, filepath);
    }

    private Tuple3<ILevel, Player, IConsciousness[]> loadState(String filepath) throws FileNotFoundException {
        return gameLoader.loadGame(filepath);
    }

    private void initGame(Tuple3<ILevel, Player, IConsciousness[]> loadedState) {
        IConsciousness player = loadedState.b;
        ILevel level = loadedState.a;
        PlayerCameraView playerCameraView = CameraViewFactory.createPlayerCameraView(player.readBody(), time);


        PlayerController playerController = ControllerFactory.createPlayerController(playerCameraView, player);
        Scene levelScene = ControllerFactory.createLevelController(level, playerCameraView, playerController);

        playerCameraView.addObserver(this);
        addAudioObserver(player.readBody(), level.readBodies());

        game.setActiveScene(levelScene);
        currentState = loadedState;
    }

    private void addAudioObserver(IReadBody playerBody, List<IReadBody> bodies) {
        Observer levelAudioController = ControllerFactory.createLevelAudioController(playerBody, bodies);
        for (IReadBody body : bodies) {
            body.addObserver(levelAudioController);
        }
    }
}
