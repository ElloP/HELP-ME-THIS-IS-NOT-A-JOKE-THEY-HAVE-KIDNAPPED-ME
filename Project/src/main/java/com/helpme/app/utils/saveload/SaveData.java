package com.helpme.app.utils.saveload;


import java.io.Serializable;

/**
 * Created by og on 2017-04-26.
 */
public class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;

    public String test1;

    private String level,tiles;


    public SaveData(String t){
        this.test1 = t;
    }

    public SaveData(){
        level = "";
        tiles = "";
    }
    public void addTile(String tile){
        tiles.concat(tile);
    }

}
