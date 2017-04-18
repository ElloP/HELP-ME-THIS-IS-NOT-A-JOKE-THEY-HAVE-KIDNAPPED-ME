package com.helpme.app.engine.screen;

import com.helpme.app.world.handler.IHandler;
import com.helpme.app.world.handler.IPlayerHandler;
import com.helpme.app.world.level.ILevel;

import java.util.Observable;

/**
 * Created by Jacob on 2017-03-30.
 */
public class WorldScreen extends Screen {
    String[] levels;
    ILevel currentLevel;
    IPlayerHandler playerHandler;
    IHandler[] handlers;

    public WorldScreen(String[] levels, IHandler playerController){
        this.levels = levels;
    }

    @Override
    public void update(){
        for(IHandler handler : handlers){
            handler.update();
        }
    }

    public void loadLevelFromFile(String filename){
        //TODO Load level into the currentLevel
    }

    @Override
    public void render() {

    }

    @Override
    public void input() {

    }
}
