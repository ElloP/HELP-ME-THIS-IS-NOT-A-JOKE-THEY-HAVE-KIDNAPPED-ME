package com.helpme.app.engine.base;

import com.helpme.app.engine.IRenderCore;
import com.helpme.app.engine.audio.AudioHandler;
import com.helpme.app.engine.input.InputHandler;

/**
 * Authored by Olle on 2017-04-02.
 * EngineCore of the engine including the main loop
 */

public class EngineCore {

    // ----------- Engine EngineCore variables and constructor -----------

    private boolean engineStopped;

    private Game game;

    private IRenderCore renderCore;

    private Time time;

    private static final double OPTIMAL_FRAMERATE = Time.SECOND / 60.0; //NOTE(Olle): sets optimal update rate (minimal) to 60 hz (or one frame per 16 ms)
    public EngineCore(IRenderCore renderCore, Game game) {
        //Note(Olle): set engine variables
        this.game = game;
        engineStopped = false;
        this.renderCore = renderCore;
        this.time = new Time();
    }

    // ----------- Engine EngineCore functions including main loop -----------


    public void start() {
        if(engineStopped) {
            return;
        }

        run();
    }

    private void run() {
        int frames = 0;
        long frameCounter = 0;

        long currentTime = time.getTime();

        while(!engineStopped) {
            //Note(Olle):initialize variables to keep track of frametimes
            long newTime = time.getTime();
            long frameTime = newTime - currentTime;
            currentTime = newTime;

            /*
             * Note(Olle): the game loop uses a catch up mechanic
             * ensuring that the model is updated at least 60 times per second,
             * so even if the frame rate is below 60 fps the model is still updated 60 times per second
             */
            while(frameTime > 0.0) {
                //Note(Olle): if the model needs to catch up, update the model
                time.deltaTime = Math.min(frameTime, OPTIMAL_FRAMERATE);
                frameCounter += time.deltaTime;
                frameTime -= time.deltaTime;

                if (Window.shouldClose()) {
                    stop();
                }

                //Note(Olle): convert deltaTime to seconds to get the correct ratios of things
                time.deltaTime = time.deltaTime / Time.SECOND;

                game.input(time);

                InputHandler.updateStates();

                game.update(time);

                if (frameCounter >= Time.SECOND) {
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            //Note(Olle): render the frame
            render();
            frames++;
            }
        //NOTE(Olle): clean up after main loop
        cleanUp();
    }

    private void stop() {
        if(engineStopped) {
            return;
        }

        engineStopped = true;
    }

    private void render() {
        renderCore.clearWindow();
        game.draw();
        Window.update();
    }

    private void cleanUp() {
        game.stop();
        Window.destroy();
        AudioHandler.cleanUp();
    }

    public Time getTime() {
        return time;
    }
}
