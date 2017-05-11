package com.helpme.app.screen.world;

import com.helpme.app.screen.IScreen;
import com.helpme.app.screen.Screen;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.IPlayer;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jacob on 2017-03-30.
 */
public class World extends Screen {
    String[] levels;
    ILevel currentLevel;
    IPlayer playerHandler;
    IConsciousness[] handlers;

    public World(String[] levels, int startingLevel, IPlayer playerHandler, IScreen[] screens) {
        super(World.class.getName(), screens);
        this.levels = levels;
        this.playerHandler = playerHandler;
        loadLevelFromFile(startingLevel);
    }

    @Override
    public void update() {
        for (IConsciousness handler : handlers) {
            handler.update();
        }
    }

    private void loadLevelFromFile(int file) {
        String name = levels[file];
        currentLevel = null;
        //TODO Load surroundings into the currentLevel
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
