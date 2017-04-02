package com.helpme.app.world;
import com.helpme.app.utilities.KeyboardHandler;
import org.lwjgl.glfw.GLFWKeyCallback;
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

    }

    public Level getLevel() {
        return levels[currentLevelIndex];
    }

    public Player getPlayer() {
        return player;
    }
}
