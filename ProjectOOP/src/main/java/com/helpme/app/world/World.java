package com.helpme.app.world;

import com.helpme.app.utilities.InputHandler;

import static org.lwjgl.glfw.GLFW.*;

import com.helpme.app.character.Player;

/**
 * Created by kopa on 2017-03-30.
 */
public class World {
    private Level[] levels;
    private int currentLevelIndex = 0;
    private Player player;

    public World(Level[] levels, Player player){
        this.levels = levels;
        this.player = player;
    }

    public void update() {
        checkInput();
    }

    public void checkInput() {
        if(InputHandler.isKeyboardKeyPress(GLFW_KEY_W)){
            getCurrentLevel().movePlayerForward();
            System.out.println(player.getPosition().toString());
        }

        if(InputHandler.isKeyboardKeyPress(GLFW_KEY_S)){
            getCurrentLevel().movePlayerBackward();
            System.out.println(player.getPosition().toString());
        }

        if(InputHandler.isKeyboardKeyPress(GLFW_KEY_A)){
            getCurrentLevel().movePlayerLeft();
            System.out.println(player.getPosition().toString());
        }

        if(InputHandler.isKeyboardKeyPress(GLFW_KEY_D)){
            getCurrentLevel().movePlayerRight();
            System.out.println(player.getPosition().toString());
        }

        if(InputHandler.isKeyboardKeyPress(GLFW_KEY_Q)){
            getCurrentLevel().rotatePlayerLeft();
            System.out.println(player.getDirection().toString());
        }

        if(InputHandler.isKeyboardKeyPress(GLFW_KEY_E)){
            getCurrentLevel().rotatePlayerRight();
            System.out.println(player.getDirection().toString());
        }
    }

    public Level getCurrentLevel() {
        return levels[currentLevelIndex];
    }

    public Player getPlayer() {
        return player;
    }
}
