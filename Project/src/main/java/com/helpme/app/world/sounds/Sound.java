package com.helpme.app.world.sounds;

/**
 * Created by Jesper on 2017-04-14.
 */
abstract public class Sound {
    private String name;
    private String fileName;

    public Sound(String name, String fileName){
        this.name = name;
        this.fileName = fileName;
    }
}
