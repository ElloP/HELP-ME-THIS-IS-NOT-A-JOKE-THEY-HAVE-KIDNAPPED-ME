package com.helpme.app.engine.input;

/**
 * Created by Jacob on 2017-04-02.
 */

import com.helpme.app.utils.functions.IFunction;
import com.helpme.app.utils.mathl.Vector2f;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public final class Input {

    private Input() {
    }

    private static Map<InputKey, Integer[]> keyDictionary;

    public static void initialize(Map<InputKey, Integer[]> keys) {
        keyDictionary = keys;
    }

    public static void setKey(InputKey input, Integer[] keycodes) {
        keyDictionary.put(input, keycodes);
    }

    public static boolean isKeyboardKeyUp(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !InputHandler.isKeyboardKeyUp(keycode) && !InputHandler.isKeyboardKeyRelease(keycode));
    }

    public static boolean isKeyboardKeyDown(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !InputHandler.isKeyboardKeyDown(keycode) && !InputHandler.isKeyboardKeyPress(keycode));
    }

    private static boolean checkKeyState(InputKey inputKey, IFunction<Integer, Boolean> failTest) {
        for (int keycode : keyDictionary.get(inputKey)) {
            if (failTest.apply(keycode)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isKeyboardKeyPress(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !InputHandler.isKeyboardKeyPress(keycode));
    }

    public static boolean isKeyboardKeyRelease(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !InputHandler.isKeyboardKeyRelease(keycode));
    }

    public static boolean isMouseButtonUp(int mousecode) {
        return InputHandler.isMouseButtonUp(mousecode) || InputHandler.isMouseButtonRelease(mousecode);
    }

    public static boolean isMouseButtonDown(int mousecode) {
        return InputHandler.isMouseButtonDown(mousecode) || InputHandler.isMouseButtonPress(mousecode);
    }

    public static boolean isMouseButtonPress(int mousecode) {
        return InputHandler.isMouseButtonPress(mousecode);
    }

    public static boolean isMouseButtonRelease(int mousecode) {
        return InputHandler.isMouseButtonRelease(mousecode);
    }

    public static boolean isMouseEntered() {
        return InputHandler.isMouseEntered();
    }

    public static Vector2f getMousePosition() {
        return InputHandler.getMousePosition();
    }

    public static Map<InputKey, Integer[]> getDefaultKeys() {
        Map<InputKey, Integer[]> defaultKeys = new HashMap<>();
        defaultKeys.put(InputKey.MOVE_FORWARD, new Integer[]{GLFW_KEY_W});
        defaultKeys.put(InputKey.MOVE_BACKWARD, new Integer[]{GLFW_KEY_S});
        defaultKeys.put(InputKey.MOVE_LEFT, new Integer[]{GLFW_KEY_A});
        defaultKeys.put(InputKey.MOVE_RIGHT, new Integer[]{GLFW_KEY_D});
        defaultKeys.put(InputKey.ROTATE_LEFT, new Integer[]{GLFW_KEY_Q});
        defaultKeys.put(InputKey.ROTATE_RIGHT, new Integer[]{GLFW_KEY_E});
        defaultKeys.put(InputKey.ATTACK, new Integer[]{GLFW_KEY_LEFT_ALT});
        defaultKeys.put(InputKey.SELFIE, new Integer[]{GLFW_KEY_LEFT_CONTROL});
        defaultKeys.put(InputKey.TALK, new Integer[]{GLFW_KEY_LEFT_SHIFT});
        defaultKeys.put(InputKey.SELECT, new Integer[]{GLFW_KEY_ENTER});

        return defaultKeys;
    }
}