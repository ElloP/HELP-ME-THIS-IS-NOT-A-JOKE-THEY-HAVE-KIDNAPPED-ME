package com.helpme.app.world;

import com.helpme.app.utilities.InputHandler;

import static org.lwjgl.glfw.GLFW.*;

import com.helpme.app.character.Player;

/**
 * Created by kopa on 2017-03-30.
 */
public class World {
    private Level[] levels;
    private int currentLevelIndex;
    private Player player;

    public void update() {
        checkInput();
    }

    public void checkInput() {
        if(InputHandler.isKeyboardKeyPressed(GLFW_KEY_W)){

        }

        if(InputHandler.isKeyboardKeyPressed(GLFW_KEY_S)){
            getCurrentLevel().movePlayerBackward();
        }

        if(InputHandler.isKeyboardKeyPressed(GLFW_KEY_A)){
            getCurrentLevel().movePlayerLeft();
        }

        if(InputHandler.isKeyboardKeyPressed(GLFW_KEY_D)){
            getCurrentLevel().movePlayerRight();
        }

        if(InputHandler.isKeyboardKeyPressed(GLFW_KEY_Q)){
            getCurrentLevel().rotatePlayerLeft();
        }

        if(InputHandler.isKeyboardKeyPressed(GLFW_KEY_E)){
            getCurrentLevel().rotatePlayerRight();
        }
    }

    public Level getCurrentLevel() {
        return levels[currentLevelIndex];
    }

    public Player getPlayer() {
        return player;
    }
}
