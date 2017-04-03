package com.helpme.app.engine.base;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Olle on 2017-04-02.
 * Core of the engine
 */
public class Core { //TODO (Olle): Move main function to a better spot
    public static void main(String args[]) {
        new Core();
    }

    private Core() {

        simpleWindow();
    }

    private void simpleWindow() {
        Window window = new Window();
        window.initWindow(800,600, "Hello World!");

        glClearColor(0.6f, 0.3f, 0.2f, 1.0f);

        while(!window.shouldClose()) {

            glClear(GL_COLOR_BUFFER_BIT);

            window.update();
        }

        window.destroy();
    }
}
