package com.helpme.app.saveload;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by og on 2017-05-09.
 */
public class SaveRoot {
    private BodyWrapper player;
    private BodyWrapper[] monsters;
    private LevelWrapper level;

    public SaveRoot(){}



    @XmlElement(name="Player")
    public BodyWrapper getPlayer(){
        return this.player;
    }
    public void setPlayer(BodyWrapper player) {
        this.player = player;
    }
    @XmlElement(name="Monsters")
    public BodyWrapper[] getMonsters() {
        return this.monsters;
    }
    public void setMonsters(BodyWrapper[] monsters) {
        this.monsters = monsters;
    }
}
