package com.helpme.app.utilities;

/**
 * Created by kopa on 2017-04-02.
 */

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardHandler extends GLFWKeyCallback {

    private enum State {DOWN, PRESSED, RELEASED, UP}

    public static State[] keys = new State[65536];


    // The GLFWKeyCallback class is an abstract method that
    // can't be instantiated by itself and must instead be extended
    //
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        // TODO Auto-generated method stub
        State state = keys[key];

        switch (keys[key]) {
            case DOWN:
                if (action == GLFW_RELEASE) {
                    state = State.RELEASED;
                }
                break;
            case UP:
                if (action != GLFW_RELEASE) {
                    state = State.PRESSED;
                }
                break;
            case PRESSED:
                state = action != GLFW_RELEASE ? State.DOWN : State.RELEASED;
                break;
            case RELEASED:
                state = action != GLFW_RELEASE ? State.PRESSED : State.UP;
                break;
        }

        keys[key] = state;
    }

    // boolean method that returns true if a given key
    // is pressed.
    public static boolean isKeyDown(int keycode) {
        return keys[keycode] == State.DOWN || keys[keycode] == State.PRESSED;
    }

    public static boolean isKeyPressed(int keycode) {
        return keys[keycode] == State.PRESSED;
    }

    public static boolean isKeyReleased(int keycode) {
        return keys[keycode] == State.RELEASED;
    }

    public static boolean isKeyUp(int keycode) {
        return keys[keycode] == State.UP || keys[keycode] == State.RELEASED;
    }

}