package com.helpme.app.engine.base;

import com.helpme.app.engine.renderer.base.Mesh;
import com.helpme.app.engine.renderer.base.RenderCore;

/**
 * Authored by Olle on 2017-04-02.
 * EngineCore of the engine including the main loop
 */
public class EngineCore {

    // ----------- Engine EngineCore variables and constructor -----------

    private Window window;

    private RenderCore renderer;

    private boolean engineStopped = false;

    private Mesh mesh;

    private final double OPTIMAL_FRAMERATE = 1 / 60.0f; //NOTE(Olle): sets optimal update rate to 60 hz (or one frame per 16 ms)

    private EngineCore() {
        start();
    }

    private float[] vertices = new float[] { //NOTE(Olle): Testing triangle
            -1.0f, -1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            1.0f, -1.0f, 0.0f
    };

    private int[] indices = new int[] {
        0,1,2
    };

    // ----------- Engine EngineCore functions including main loop -----------

    private void start() {
        if(engineStopped) {
            return;
        }

        window = new Window();
        window.initWindow(800,600, "Hello World!");

        renderer = new RenderCore();

        mesh = new Mesh(vertices, indices);

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

        mesh.destroy();
        window.destroy(); //NOTE(Olle): clean up after main loop
    }

    private void stop() {
        if(engineStopped) {
            return;
        }

        engineStopped = true;
    }

    private void render() {
        renderer.clearWindow();
        mesh.draw();
        window.update();
    }

    public static void main(String args[]) {
        new EngineCore();
    }
}
