package com.helpme.app.engine.base;

import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputHandler;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Authored by Olle on 2017-04-02.
 */
public class Window {

    // ----------- Window essentials -----------

    private static long window;

    public static int width;
    public static int height;

    public static long getWindow() { return window; }

    static boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    // ----------- Initiation, execute and clean up -----------

    public static void initWindow(int width, int height, String title) {
        if(!glfwInit()) {
            throw new IllegalStateException("GLFW failed to initialize!");
        }

        Window.width = width;
        Window.height = height;

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); //NOTE(Olle): sets opengl version
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); //NOTE(Olle): forward compatibility needed on certain versions of OS X
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE); //NOTE(Olle): base profile disables legacy openGL operations
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); //NOTE(Olle):disables resizability

        window = glfwCreateWindow(width, height, title, NULL, NULL); //NOTE(Olle): creates and sets the window variable
        if(window == NULL) {
            throw new RuntimeException("GLFW failed to create window");
        }

        InputHandler.initialize(window);
        Input.initialize(Input.getDefaultKeys());

        glfwMakeContextCurrent(window);

        GL.createCapabilities();
    }

    public static void update() { //NOTE(Olle): tells window to swap the frame buffers and get input from mouse and keyboard
        glfwSwapBuffers(window);

        glfwPollEvents();
    }

    public static void destroy() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
    }

    // ----------- Settings -----------

    public static void lockMouse() {
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public static void unlockMouse() {
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public static void enableVSync() {
        glfwSwapInterval(1);
    }

    public static void disableVSync() {
        glfwSwapInterval(0);
    }

}
