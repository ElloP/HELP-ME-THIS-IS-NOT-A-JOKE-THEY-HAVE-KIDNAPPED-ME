package com.helpme.app.game.controller;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.concrete.Player;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.saveload.SaveLoad;
import com.helpme.app.game.view.camera.CameraViewFactory;
import com.helpme.app.game.view.camera.PlayerCameraView;
import com.helpme.app.game.view.resources.Resources;
import com.helpme.app.utils.tuple.Tuple3;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-22.
 */

public class GameController extends Game implements Observer {
    private static final String START_LEVEL = "level1.xml";
    private static final String SAVED_LEVEL = "saved_game.xml";
    private SaveLoad gameLoader;

    Time time;

    private Tuple3<ILevel, Player, IConsciousness[]> currentState;

    public GameController(SaveLoad gameLoader) {
        super(new MenuController(), new Camera());
        this.gameLoader = gameLoader;
        scene.addObserver(this);
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
                    throw new RuntimeException("Couldn't load " + START_LEVEL + " in GameController.update::" + e);
                }
            } else if (((MenuController) o).getCurrent() == 0) {

                try {
                    startGame(SAVED_LEVEL);
                } catch (FileNotFoundException e0) {
                    System.out.println("Couldn't load " + SAVED_LEVEL + " in GameController.update::" + e0);
                    try {
                        startGame(START_LEVEL);
                    }catch (FileNotFoundException e1){
                        throw new RuntimeException("Couldn't load " + START_LEVEL + " in GameController.update::" + e1);
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
        IReadBody playerBody = player.readBody();
        activeCamera = new Camera(playerBody.readPosition(), playerBody.readDirection());
        PlayerCameraView playerCameraView = CameraViewFactory.createPlayerCameraView(activeCamera, time);


        PlayerController playerController = ControllerFactory.createPlayerController(playerCameraView, player);
        Scene levelScene = ControllerFactory.createLevelController(level, playerCameraView, playerController);

        playerCameraView.addObserver(this);
        addAudioObserver(player.readBody(), level.readBodies());

        this.setActiveScene(levelScene);
        currentState = loadedState;
    }

    private void addAudioObserver(IReadBody playerBody, List<IReadBody> bodies) {
        Observer levelAudioController = ControllerFactory.createLevelAudioController(playerBody, bodies);
        for (IReadBody body : bodies) {
            body.addObserver(levelAudioController);
        }
    }

    @Override
    public void input(Time time) {
        scene.input(time);
    }

    @Override
    public void update(Time time) {
        if(this.time == null) {
            this.time = time;
        }
    }
}
