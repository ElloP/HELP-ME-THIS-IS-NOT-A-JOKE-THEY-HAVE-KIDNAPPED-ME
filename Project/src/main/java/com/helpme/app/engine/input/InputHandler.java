package com.helpme.app.engine.input;

/**
 * Created by Jacob on 2017-04-02.
 */

import com.helpme.app.utils.functions.IFunction;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {

    private InputHandler() {
    }

    private static Map<InputKey, Integer[]> keyDictionary;

    public static void initialize(Map<InputKey, Integer[]> keys) {
        keyDictionary = keys;
    }

    public static void setKey(InputKey input, Integer[] keycodes) {
        keyDictionary.put(input, keycodes);
    }

    public static boolean isKeyboardKeyUp(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !KeyState.isKeyboardKeyUp(keycode) && !KeyState.isKeyboardKeyRelease(keycode));
    }

    public static boolean isKeyboardKeyDown(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !KeyState.isKeyboardKeyDown(keycode) && !KeyState.isKeyboardKeyPress(keycode));
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
        return checkKeyState(inputKey, keycode -> !KeyState.isKeyboardKeyPress(keycode));
    }

    public static boolean isKeyboardKeyRelease(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !KeyState.isKeyboardKeyRelease(keycode));
    }

    public static boolean isMouseButtonUp(int mousecode) {
        return KeyState.isMouseButtonUp(mousecode) || KeyState.isMouseButtonRelease(mousecode);
    }

    public static boolean isMouseButtonDown(int mousecode) {
        return KeyState.isMouseButtonDown(mousecode) || KeyState.isMouseButtonPress(mousecode);
    }

    public static boolean isMouseButtonPress(int mousecode) {
        return KeyState.isMouseButtonPress(mousecode);
    }

    public static boolean isMouseButtonRelease(int mousecode) {
        return KeyState.isMouseButtonRelease(mousecode);
    }

    public static Map<InputKey, Integer[]> getDefaultKeys() {
        Map<InputKey, Integer[]> defaultKeys = new HashMap<>();
        defaultKeys.put(InputKey.MoveForward, new Integer[]{GLFW_KEY_W});
        defaultKeys.put(InputKey.MoveBackward, new Integer[]{GLFW_KEY_W});
        defaultKeys.put(InputKey.MoveLeft, new Integer[]{GLFW_KEY_W});
        defaultKeys.put(InputKey.MoveRight, new Integer[]{GLFW_KEY_W});
        defaultKeys.put(InputKey.RotateLeft, new Integer[]{GLFW_KEY_Q});
        defaultKeys.put(InputKey.RotateRight, new Integer[]{GLFW_KEY_E});
        defaultKeys.put(InputKey.Attack, new Integer[]{GLFW_KEY_LEFT_ALT});
        defaultKeys.put(InputKey.Selfie, new Integer[]{GLFW_KEY_LEFT_CONTROL});
        defaultKeys.put(InputKey.Talk, new Integer[]{GLFW_KEY_LEFT_SHIFT});

        return defaultKeys;
    }
}