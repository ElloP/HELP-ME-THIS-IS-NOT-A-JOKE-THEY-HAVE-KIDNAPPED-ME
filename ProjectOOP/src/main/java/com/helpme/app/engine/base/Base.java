package com.helpme.app.engine.base;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Olle on 2017-04-02.
 */
public class Base {
    public static void main(String args[]) {
        new Base();
    }

    private Base() {
        simpleWindow();
    }

    private void simpleWindow() {
        Window window = new Window();
        window.initWindow(800,600, "Hello World!");

        glClearColor(0.6f, 0.3f, 0.2f, 1.0f);

        while(!window.shouldClose()) {

            glClear(GL_COLOR_BUFFER_BIT);

            glfwPollEvents();

            glfwSwapBuffers(window.getWindow());
        }

        window.destroy();
    }
}
