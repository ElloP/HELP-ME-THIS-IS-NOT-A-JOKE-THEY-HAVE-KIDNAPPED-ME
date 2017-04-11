package com.helpme.app.world;

/**
 * Created by Jacob on 2017-03-30.
 */
public class World {
    ILevel[] levels;
    IController playerController;
    int currentLevelIndex;

    public World(ILevel[] levels, IController playerController){
        this.levels = levels;
    }

    public void update(){
        playerController.update();
    }
}
