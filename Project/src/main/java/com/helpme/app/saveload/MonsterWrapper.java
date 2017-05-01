package com.helpme.app.saveload;

import com.helpme.app.world.character.IReadMonster;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-05-01.
 */
public class MonsterWrapper {
    private boolean isDead;
    private String dialogue;
    PlayerWrapper pw;

    public MonsterWrapper(){}

    public MonsterWrapper(IReadMonster monster){
        this.isDead = monster.isDead();
        this.dialogue = "temporary"; //monster.getDialogue().toString(); //TODO (klas)
        this.pw = new PlayerWrapper(monster);
    }
    @XmlElement(name="isDead")
    public boolean getIsDead(){
        return isDead;
    }
    @XmlElement(name="Dialogue")
    public String getDialogue(){
        return dialogue;
    }
    public String toString(){
        String result = "";
        result += "isDead: " + isDead;
        result += "\nDialogue: " + dialogue;
        return result;
    }
}
