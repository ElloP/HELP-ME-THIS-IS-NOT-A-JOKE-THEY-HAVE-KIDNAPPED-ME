package com.helpme.app.engine.base;

import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by Olle on 2017-04-02.
 */
public class Window {

    // ----------- Window essentials -----------

    private long window;

    public long getWindow() { return window; }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    // ----------- Initiation, update and clean up -----------

    public void initWindow(int width, int height, String title) {
        if(!glfwInit()) {
            throw new IllegalStateException("GLFW failed to initialize!");
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); //sets opengl version
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); //forward compatibility needed on certain versions of OS X
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); //core profile disables legacy openGL operations
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); //disables resizability

        window = glfwCreateWindow(width, height, title, NULL, NULL); //creates and sets the window variable
        if(window == NULL) {
            throw new RuntimeException("GLFW failed to create window");
        }

        exitWithESC();

        glfwMakeContextCurrent(window);

        GL.createCapabilities();
    }

    public void update() { //tells window to swap the framebuffers and get input from mouse and keyboard
        glfwPollEvents();

        glfwSwapBuffers(window);
    }

    public void destroy() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
    }

    // ----------- Settings -----------

    public void exitWithESC() { //closes window with ESC. good to have during development
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
        });
    }

    public void lockMouse() {
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public void unlockMouse() {
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public void enableVSync() {
        glfwSwapInterval(1);
    }

    public void disableVSync() {
        glfwSwapInterval(0);
    }

}
