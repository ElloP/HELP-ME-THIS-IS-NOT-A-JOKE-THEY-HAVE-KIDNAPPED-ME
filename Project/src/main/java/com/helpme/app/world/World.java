package com.helpme.app.world;

import com.helpme.app.world.handler.IHandler;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jacob on 2017-03-30.
 */
public class World {
    ILevel[] levels;
    IHandler playerHandler;
    int currentLevelIndex;

    public World(ILevel[] levels, IHandler playerController){
        this.levels = levels;
    }

    public void update(){
        playerHandler.update();
    }
}
