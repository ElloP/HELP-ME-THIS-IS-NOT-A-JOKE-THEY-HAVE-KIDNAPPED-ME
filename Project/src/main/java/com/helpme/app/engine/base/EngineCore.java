package com.helpme.app.engine.base;

import com.helpme.app.engine.renderer.base.RenderCore;
import com.helpme.app.utils.mathl.Matrix4f;

/**
 * Authored by Olle on 2017-04-02.
 * EngineCore of the engine including the main loop
 */
public class EngineCore {

    // ----------- Engine EngineCore variables and constructor -----------

    private boolean engineStopped;

    private Game game;

    private final double OPTIMAL_FRAMERATE = Time.SECOND / 60.0; //NOTE(Olle): sets optimal update rate (minimal) to 60 hz (or one frame per 16 ms)

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
        long frameCounter = 0;

        long currentTime = Time.getTime();

        while(!engineStopped) {
            long newTime = Time.getTime();
            long frameTime = newTime - currentTime;
            currentTime = newTime;

            while(frameTime > 0.0) {

                Time.deltaTime = Math.min(frameTime, OPTIMAL_FRAMERATE);
                frameCounter += Time.deltaTime;
                frameTime -= Time.deltaTime;

                if (Window.shouldClose()) {
                    stop();
                }

                Time.deltaTime = Time.deltaTime / Time.SECOND; //Note(Olle): convert deltaTime to seconds to get the correct ratios of things

                //TODO(Olle): Update inputhandler
                game.input();
                game.update();

                if (frameCounter >= Time.SECOND) {
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
        /*Window.initWindow(800,600, "Hello World!");
        Window.disableVSync();

        EngineCore ec = new EngineCore();
        ec.start();*/

        Matrix4f mat = new Matrix4f();
        Matrix4f mat2 = new Matrix4f();

        mat.add(mat2);
        mat.identity();

        mat.logMatrix();

    }
}
