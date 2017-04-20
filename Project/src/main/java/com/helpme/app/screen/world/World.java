package com.helpme.app.screen.world;

import com.helpme.app.screen.IScreen;
import com.helpme.app.screen.Screen;
import com.helpme.app.world.handler.IHandler;
import com.helpme.app.world.handler.IPlayerHandler;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jacob on 2017-03-30.
 */
public class World extends Screen {
    String[] levels;
    ILevel currentLevel;
    IPlayerHandler playerHandler;
    IHandler[] handlers;

    public World(String[] levels, int startingLevel, IPlayerHandler playerHandler, IScreen[] screens) {
        super(World.class.getName(), screens);
        this.levels = levels;
        this.playerHandler = playerHandler;
        loadLevelFromFile(startingLevel);
    }

    @Override
    public void update() {
        for (IHandler handler : handlers) {
            handler.update();
        }
    }

    private void loadLevelFromFile(int file) {
        String name = levels[file];
        currentLevel = null;
        //TODO Load level into the currentLevel
    }

    @Override
    public void render() {
        //TODO
    }

    @Override
    public void input() {
        //TODO
    }
}
