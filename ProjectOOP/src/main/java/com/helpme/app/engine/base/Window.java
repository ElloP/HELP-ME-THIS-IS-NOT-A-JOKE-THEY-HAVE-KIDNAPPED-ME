package com.helpme.app.engine.base;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by Olle on 2017-04-02.
 */
public class Window {

    private long window;

    public long getWindow() { return window; }

    public boolean shouldClose() {
        return false;
    }

    public void initWindow() {
    }

    public void lockMouse() {

    }

    public void enableVSync() {
    }

    public void disableVSync() {
    }

}
