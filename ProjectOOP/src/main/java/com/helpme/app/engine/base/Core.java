package com.helpme.app.engine.base;

/**
 * Authored by Olle on 2017-04-02.
 * Core of the engine including the main loop
 */
public class Core {

    // ----------- Engine Core variables and constructor -----------

    private Window window;

    private boolean engineStopped = false;

    private final double OPTIMAL_FRAMERATE = 1 / 60.0f; //NOTE(Olle): sets optimal update rate to 60 hz (or one frame per 16 ms)

    private Core() {
        start();
    }

    // ----------- Engine Core functions including main loop -----------

    private void start() {
        if(engineStopped) {
            return;
        }

        window = new Window();
        window.initWindow(800,600, "Hello World!");

        run();
    }

    private void run() {
        int frames = 0;
        double frameCounter = 0.0;

        double currentTime = Time.getTime();

        while(!engineStopped) {
            double newTime = Time.getTime();
            double frameTime = newTime - currentTime;
            currentTime = newTime;

            frameCounter += frameTime;

            while(frameTime > 0.0) {
                if(window.shouldClose()) {
                    stop();
                }

                Time.deltaTime = Math.min(frameTime, OPTIMAL_FRAMERATE);

                //TODO (Olle): get input
                //TODO (Olle): update game state

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
        window.destroy(); // clean up after main loop
    }

    private void stop() {
        if(engineStopped) {
            return;
        }

        engineStopped = true;
    }

    private void render() {
        window.update();
    }

    public static void main(String args[]) {
        new Core();
    }
}
