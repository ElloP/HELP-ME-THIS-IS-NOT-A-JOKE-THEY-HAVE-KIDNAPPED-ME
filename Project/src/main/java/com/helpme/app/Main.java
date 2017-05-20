package com.helpme.app;

import com.helpme.app.engine.base.EngineCore;
import com.helpme.app.engine.base.Game;
import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.base.Window;
import com.helpme.app.engine.game.GameInstance;
import com.helpme.app.engine.game.controls.PlayerController;
import com.helpme.app.engine.renderer.base.RenderCore;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.world.level.ILevel;

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
        Game game = new GameInstance(level, time);
        GameController gameController = new GameController((PlayerController) ((GameInstance)game).getCameraController(), setup.player, level);

        for (IReadBody body : level.readBodies()) {
            ((Body)body).addObserver(gameController);
        }

        EngineCore ec = new EngineCore(RenderCore.getRenderCore(), game, time);
        ec.start();
    }
}
