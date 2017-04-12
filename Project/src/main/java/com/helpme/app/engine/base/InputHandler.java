package com.helpme.app.engine.base;

/**
 * Created by Jacob on 2017-04-02.
 */

import com.helpme.app.utils.Vector2f;
import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;


/**
 * Authored by Jacob on 2017-04-03
 */

public class InputHandler {
    private enum State {DOWN, PRESS, RELEASE, UP} //TODO(Olle): put in own file

    private static boolean entered = true;
    private static Vector2f mousePosition = new Vector2f(0.0f, 0.0f); //TODO(Olle): change this to another datatype
    private static State[] keyboardKeys = new State[65536];
    private static State[] mouseButtons = new State[8];

    private InputHandler() {}

    public static void init(long window) {
        Arrays.fill(keyboardKeys, State.UP);
        Arrays.fill(mouseButtons, State.UP);

        setCallBacks(window);
    }

    private static void setCallBacks(long window) {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (l, i, i1, i2, i3) -> InputHandler.setKeyboardKey(l, i, i1, i2, i3));

        // Setup a cursor position callback. It will be called every time the mouse cursor position changes
        glfwSetCursorPosCallback(window, (l, v, v1) -> InputHandler.setCursorPosition(l, v, v1));

        // Setup a cursor enter callback. It will be called every time the mouse cursor enters or leaves the window
        glfwSetCursorEnterCallback(window, (l, b) -> InputHandler.setCursorEntered(l, b));

        // Setup a mouse button callback. It will be called every time a mouse button is clicked
        glfwSetMouseButtonCallback(window, (l, i, i1, i2) -> InputHandler.setMouseButton(l, i, i1, i2));

    }

    public static void update() {
        updateState(mouseButtons);
        updateState(keyboardKeys);
    }

    private static void updateState(State[] list) {
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

    private static void setState(State[] list, int index, int action) {
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

    private static void setMouseButton(long window, int button, int action, int mods) {
        setState(mouseButtons, button, action);
    }

    private static void setKeyboardKey(long window, int key, int scancode, int action, int mods) {
        setState(keyboardKeys, key, action);
    }

    private static void setCursorEntered(long window, boolean entered) {
        InputHandler.entered = entered;
    }

    private static void setCursorPosition(long window, double xPos, double yPos) {
        mousePosition.x = (float) xPos;
        mousePosition.y = (float) yPos; //TODO(Olle): Cast in vector class instead
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

    public static Vector2f getMousePosition() {
        return mousePosition.clone();
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