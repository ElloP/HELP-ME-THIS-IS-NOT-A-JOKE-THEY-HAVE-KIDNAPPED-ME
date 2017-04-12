package com.helpme.app.engine.input;

import com.helpme.app.utils.Vector2f;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Created by Jacob on 2017-04-12.
 */
public class KeyState {
    private static boolean entered = true;

    private KeyState() {

    }

    private static InputState[] keyboardKeys = new InputState[65536];
    private static InputState[] mouseButtons = new InputState[8];
    private static Vector2f mousePosition = new Vector2f(0, 0);

    static void Init() {
        Arrays.fill(keyboardKeys, InputState.UP);
        Arrays.fill(mouseButtons, InputState.UP);
    }

    static void setKeyboardKey(long window, int key, int scancode, int action, int mods) {
        setState(keyboardKeys, key, action);
    }

    static void setMouseButton(long window, int button, int action, int mods) {
        setState(mouseButtons, button, action);
    }

    static void updateAllStates() {
        updateState(mouseButtons);
        updateState(keyboardKeys);
    }

    private static void updateState(InputState[] list) {
        for (int key = 0; key < list.length; key++) {
            switch (list[key]) {
                case PRESS:
                    list[key] = InputState.DOWN;
                    break;
                case RELEASE:
                    list[key] = InputState.UP;
                    break;
            }
        }
    }

    private static void setState(InputState[] list, int index, int action) {
        switch (list[index]) {
            case DOWN:
                if (action == GLFW_RELEASE) {
                    list[index] = InputState.RELEASE;
                }
                break;
            case UP:
                if (action != GLFW_RELEASE) {
                    list[index] = InputState.PRESS;
                }
                break;
        }
    }

    static void setCursorEntered(long window, boolean enteredState) {
        entered = enteredState;
    }

    static void setCursorPosition(long window, double xPos, double yPos) {
        mousePosition.x = (int) Math.round(xPos);
        mousePosition.y = (int) Math.round(yPos);
    }

    static boolean isKeyboardKeyUp(int keycode) {
        return keyboardKeys[keycode] == InputState.UP || keyboardKeys[keycode] == InputState.RELEASE;
    }

    static boolean isKeyboardKeyDown(int keycode) {
        return keyboardKeys[keycode] == InputState.DOWN || keyboardKeys[keycode] == InputState.PRESS;
    }

    static boolean isKeyboardKeyPress(int keycode) {
        return keyboardKeys[keycode] == InputState.PRESS;
    }

    static boolean isKeyboardKeyRelease(int keycode) {
        return keyboardKeys[keycode] == InputState.RELEASE;
    }

    static boolean isMouseButtonUp(int mousecode) {
        return mouseButtons[mousecode] == InputState.UP || mouseButtons[mousecode] == InputState.RELEASE;
    }

    static boolean isMouseButtonDown(int mousecode) {
        return mouseButtons[mousecode] == InputState.DOWN || mouseButtons[mousecode] == InputState.PRESS;
    }

    static boolean isMouseButtonPress(int mousecode) {
        return mouseButtons[mousecode] == InputState.PRESS;
    }

    static boolean isMouseButtonRelease(int mousecode) {
        return mouseButtons[mousecode] == InputState.RELEASE;
    }
}

