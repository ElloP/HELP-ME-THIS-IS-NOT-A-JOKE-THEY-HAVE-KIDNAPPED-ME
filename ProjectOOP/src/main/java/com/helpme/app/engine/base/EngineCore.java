package com.helpme.app.engine.base;

import com.helpme.app.engine.renderer.base.RenderCore;

/**
 * Authored by Olle on 2017-04-02.
 * EngineCore of the engine including the main loop
 */
public class EngineCore {

    // ----------- Engine EngineCore variables and constructor -----------

    private boolean engineStopped;

    private Game game;

    private final double OPTIMAL_FRAMERATE = 1 / 60.0f; //NOTE(Olle): sets optimal update rate to 60 hz (or one frame per 16 ms)

    public EngineCore() {
        RenderCore.init();
        engineStopped = false;
        game = new Game();
    }


    // ----------- Engine EngineCore functions including main loop -----------

    private void start() {
        if(engineStopped) {
            return;
        }

        run();
    }

    private void run() {
        int frames = 0;
        double frameCounter = 0.0;

        double currentTime = Time.getTimeInSeconds();

        while(!engineStopped) {
            double newTime = Time.getTimeInSeconds();
            double frameTime = newTime - currentTime;
            currentTime = newTime;

            frameCounter += frameTime;

            while(frameTime > 0.0) {
                if(Window.shouldClose()) {
                    stop();
                }

                Time.deltaTime = Math.min(frameTime, OPTIMAL_FRAMERATE);

                game.input();
                game.update();

                frameTime -= Time.deltaTime;
                if(frameCounter >= 1.0) {
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
        Window.initWindow(800,600, "Hello World!");

        EngineCore ec = new EngineCore();
        ec.start();

    }
}
