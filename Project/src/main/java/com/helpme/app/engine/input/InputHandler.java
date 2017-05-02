package com.helpme.app.engine.input;

import com.helpme.app.utils.Vector2f;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Jacob on 2017-04-12.
 */
public final class InputHandler {
    private static boolean entered = true;

    private InputHandler() {

    }

    private static InputState[] keyboardKeys = new InputState[65536];
    private static InputState[] mouseButtons = new InputState[8];
    private static Vector2f mousePosition = new Vector2f(0, 0);

    public static void initialize(long window) {
        Arrays.fill(keyboardKeys, InputState.UP);
        Arrays.fill(mouseButtons, InputState.UP);

        glfwSetKeyCallback(window, (w, k, s, a, m) -> setKeyboardKey(w, k, s, a, m));
        glfwSetMouseButtonCallback(window, (w, b, a, m) -> setMouseButton(w, b, a, m));
        glfwSetCursorPosCallback(window, (w, x, y) -> setCursorPosition(w, x, y));
        glfwSetCursorEnterCallback(window, (w, e) -> setCursorEntered(w, e));
    }

    public static void setKeyboardKey(long window, int key, int scancode, int action, int mods) {
        setState(keyboardKeys, key, action);
    }

    public static void setMouseButton(long window, int button, int action, int mods) {
        setState(mouseButtons, button, action);
    }

    public static void updateStates() {
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

    public static void setCursorEntered(long window, boolean enteredState) {
        entered = enteredState;
    }

    public static void setCursorPosition(long window, double xPos, double yPos) {
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

    static boolean isMouseEntered(){
        return entered;
    }

    static Vector2f getMousePosition(){
        return mousePosition.clone();
    }
}

