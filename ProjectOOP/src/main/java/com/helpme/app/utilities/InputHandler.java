package com.helpme.app.utilities;

/**
 * Created by kopa on 2017-04-02.
 */

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {
    private enum State {DOWN, PRESS, RELEASE, UP}

    private static boolean entered = true;
    private static Coordinate mousePosition = new Coordinate();
    private static State[] keyboardKeys = new State[65536];
    private static State[] mouseButtons = new State[8];

    public InputHandler() {
        Arrays.fill(keyboardKeys, State.UP);
        Arrays.fill(mouseButtons, State.UP);
    }

    public void setKeyboardKey(long window, int key, int scancode, int action, int mods) {
        setState(keyboardKeys, key, action);
    }

    public void setMouseButton(long window, int button, int action, int mods) {
        setState(mouseButtons, button, action);
    }

    public void update() {
        updateState(mouseButtons);
        updateState(keyboardKeys);
    }

    private void updateState(State[] list) {
        for (int key = 0; key < list.length; key++) {
            switch (list[key]) {
                case PRESS:
                    list[key] = State.DOWN;
                    break;
                case RELEASE:
                    list[key] = State.UP;
                    break;
            }
        }
    }

    private void setState(State[] list, int index, int action) {
        switch (list[index]) {
            case DOWN:
                if (action == GLFW_RELEASE) {
                    list[index] = State.RELEASE;
                }
                break;
            case UP:
                if (action != GLFW_RELEASE) {
                    list[index] = State.PRESS;
                }
                break;
        }
    }

    public void setCursorEntered(long window, boolean entered) {
        this.entered = entered;
    }

    public void setCursorPosition(long window, double xPos, double yPos) {
        mousePosition.x = (int) Math.round(xPos);
        mousePosition.y = (int) Math.round(yPos);
    }

    public static boolean isKeyboardKeyUp(int keycode) {
        return keyboardKeys[keycode] == State.UP || keyboardKeys[keycode] == State.RELEASE;
    }

    public static boolean isKeyboardKeyDown(int keycode) {
        return keyboardKeys[keycode] == State.DOWN || keyboardKeys[keycode] == State.PRESS;
    }

    public static boolean isKeyboardKeyPress(int keycode) {
        return keyboardKeys[keycode] == State.PRESS;
    }

    public static boolean isKeyboardKeyRelease(int keycode) {
        return keyboardKeys[keycode] == State.RELEASE;
    }

    public static boolean isMouseButtonUp(int mousecode) {
        return mouseButtons[mousecode] == State.UP || mouseButtons[mousecode] == State.RELEASE;
    }

    public static boolean isMouseButtonDown(int mousecode) {
        return mouseButtons[mousecode] == State.DOWN || mouseButtons[mousecode] == State.PRESS;
    }

    public static boolean isMouseButtonPress(int mousecode) {
        return mouseButtons[mousecode] == State.PRESS;
    }
    public static boolean isMouseButtonRelease(int mousecode) {
        return mouseButtons[mousecode] == State.RELEASE;
    }



}