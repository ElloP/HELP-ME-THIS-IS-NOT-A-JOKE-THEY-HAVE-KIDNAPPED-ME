package com.helpme.app.engine.input;

/**
 * Created by Jacob on 2017-04-02.
 */

import java.util.Map;

public class InputHandler {

    private InputHandler() {
    }

    private static Map<InputKey, Integer[]> keyDictionary;

    public static void Initialize(Map<InputKey, Integer[]> keys){
        keyDictionary = keys;
    }

    public static boolean isKeyboardKeyUp(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !KeyState.isKeyboardKeyUp(keycode) && !KeyState.isKeyboardKeyRelease(keycode));
    }

    public static boolean isKeyboardKeyDown(InputKey inputKey) {
        return checkKeyState(inputKey, keycode -> !KeyState.isKeyboardKeyDown(keycode) && !KeyState.isKeyboardKeyPress(keycode));
    }

    private static boolean checkKeyState(InputKey inputKey, IKeyCheck failTest){
        for(int keycode : keyDictionary.get(inputKey)){
            if(failTest.check(keycode)){
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
}