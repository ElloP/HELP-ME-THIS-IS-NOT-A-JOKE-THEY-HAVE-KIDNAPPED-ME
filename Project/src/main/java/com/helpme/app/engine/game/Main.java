package com.helpme.app.engine.game;

import com.helpme.app.engine.base.EngineCore;
import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Window;
import com.helpme.app.engine.renderer.base.RenderCore;

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
        EngineCore ec = new EngineCore(RenderCore.getRenderCore(), game);
        ec.start();
    }
}
