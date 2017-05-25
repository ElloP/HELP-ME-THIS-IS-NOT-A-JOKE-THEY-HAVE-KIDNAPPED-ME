package com.helpme.app;

import com.helpme.app.engine.base.EngineCore;
import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Window;
import com.helpme.app.engine.renderer.base.RenderCore;
import com.helpme.app.game.GameInstance;
import com.helpme.app.game.controller.SceneController;
import com.helpme.app.game.saveload.GameLoader;
import com.helpme.app.game.saveload.SaveLoad;
import com.helpme.app.game.view.resources.Resources;

import java.util.Observer;

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

        Game game = new GameInstance();
        SaveLoad gameLoader = new GameLoader();

        EngineCore engineCore = new EngineCore(RenderCore.getRenderCore(), game);
        Resources.init();
        new SceneController(game, gameLoader, engineCore.getTime());
        engineCore.start();
    }
}
