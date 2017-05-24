package com.helpme.app;

import com.helpme.app.controller.IController;
import com.helpme.app.controller.PlayerController;
import com.helpme.app.controller.SceneController;
import com.helpme.app.engine.base.*;
import com.helpme.app.engine.game.GameInstance;
import com.helpme.app.engine.game.scenes.Menu;
import com.helpme.app.engine.renderer.base.RenderCore;
import com.helpme.app.engine.sounds.AudioObserver;
import com.helpme.app.engine.sounds.audio.AudioHandler;
import com.helpme.app.engine.sounds.sources.AbstractMonsterSource;
import com.helpme.app.engine.sounds.sources.MonsterSource;
import com.helpme.app.engine.sounds.sources.PlayerSource;
import com.helpme.app.engine.sounds.sources.Source;
import com.helpme.app.saveload.GameLoader;
import com.helpme.app.saveload.SaveLoad;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.world.level.ILevel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Authored by Olle on 2017-05-18.
 */
public class Main {
    private static final int WINDOW_WIDTH = 1600;
    private static final int WINDOW_HEIGHT = 900;
    private static final String WINDOW_TITLE = "Help me this is not a joke!";

    public static void main(String[] args) {
        Window.initWindow(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE);
        Window.disableVSync();
        Setup setup = new Setup();
        ILevel level = setup.setup();

        Time time = new Time();
        Scene menu = new Menu();
        Game game = new GameInstance();
        SaveLoad gameLoader = new GameLoader();
        //IController playerController = new PlayerController()

        //IController sceneController = new SceneController(game, )
        //PlayerController playerController = new PlayerController((PlayerCamera) ((GameInstance)game).getCameraController(), setup.getPlayer(), level);

        try {
            AudioHandler.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AudioHandler.setListenerPos(setup.getPlayerBody().readPosition().x, setup.getPlayerBody().readPosition().y, 0);
        int walkBuffer = 0;
        int groanBuffer = 0;
        try {
            walkBuffer = AudioHandler.loadSound("src\\main\\java\\com\\helpme\\app\\engine\\sounds\\files\\Cowboy.wav");
            groanBuffer = AudioHandler.loadSound("src\\main\\java\\com\\helpme\\app\\engine\\sounds\\files\\Groan.wav");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<AbstractMonsterSource> bodySources = new ArrayList<>();
        for (IReadBody body : level.readBodies()) {
            bodySources.add(new MonsterSource(body, new Source(), walkBuffer, -1, groanBuffer, body.readPosition()));
        }
        bodySources.add(new PlayerSource(setup.getPlayerBody(), new Source(), walkBuffer, -1, groanBuffer, setup.getPlayerBody().readPosition()));
        AudioObserver audioObserver = new AudioObserver(bodySources);



        for (IReadBody body : level.readBodies()) {
            //((Body)body).addObserver(playerController);
            ((Body)body).addObserver(audioObserver);
        }
        ((Body)setup.getPlayerBody()).addObserver(audioObserver);

        EngineCore ec = new EngineCore(RenderCore.getRenderCore(), game, time);
        IController sceneController = new SceneController(game, gameLoader, ec);
        ec.start();
        AudioHandler.cleanUp();
    }
}
