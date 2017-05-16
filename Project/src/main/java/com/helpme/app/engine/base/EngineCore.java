package com.helpme.app.engine.base;

import com.helpme.app.engine.game.GameInstance;
import com.helpme.app.engine.input.InputHandler;
import com.helpme.app.engine.renderer.base.RenderCore;

/**
 * Authored by Olle on 2017-04-02.
 * EngineCore of the engine including the main loop
 */
public class EngineCore {

    // ----------- Engine EngineCore variables and constructor -----------

    private boolean engineStopped;

    private Game game;

    private final double OPTIMAL_FRAMERATE = Time.SECOND / 60.0; //NOTE(Olle): sets optimal update rate (minimal) to 60 hz (or one frame per 16 ms)

    public EngineCore(Game game) {
        RenderCore.init();
        engineStopped = false;
        this.game = game;
    }


    // ----------- Engine EngineCore functions including main loop -----------

    private void start() {
        if(engineStopped) {
            return;
        }

        run();
    }

    private void run() {
        Time time = new Time();
        int frames = 0;
        long frameCounter = 0;

        long currentTime = time.getTime();

        while(!engineStopped) {
            long newTime = time.getTime();
            long frameTime = newTime - currentTime;
            currentTime = newTime;

            while(frameTime > 0.0) {

                time.deltaTime = Math.min(frameTime, OPTIMAL_FRAMERATE);
                frameCounter += time.deltaTime;
                frameTime -= time.deltaTime;

                if (Window.shouldClose()) {
                    stop();
                }

                time.deltaTime = time.deltaTime / Time.SECOND; //Note(Olle): convert deltaTime to seconds to get the correct ratios of things

                game.input(time);

                InputHandler.updateStates();

                game.update(time);

                if (frameCounter >= Time.SECOND) {
                    //TODO(Olle): render frames ingame instead of sout
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            render();
            frames++;
            }
        cleanUp(); //NOTE(Olle): clean up after main loop
    }

    private void stop() {
        if(engineStopped) {
            return;
        }

        engineStopped = true;
    }

    private void render() {
        RenderCore.clearWindow();
        game.draw();
        Window.update();
    }

    private void cleanUp() {
        Window.destroy();
    }

    public static void main(String args[]) {
        Window.initWindow(1600,900, "Help me this is not a joke!");
        Window.enableVSync();

        Game game = new GameInstance();

        EngineCore ec = new EngineCore(game);
        ec.start();
    }
}
