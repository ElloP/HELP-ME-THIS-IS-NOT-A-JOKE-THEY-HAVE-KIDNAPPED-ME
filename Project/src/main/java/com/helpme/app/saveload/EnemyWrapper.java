package com.helpme.app.saveload;

import com.helpme.app.world.character.IReadBody;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-05-01.
 */
public class EnemyWrapper {
    private boolean isDead;
    private String dialogue;
    BodyWrapper body;

    public EnemyWrapper(){}

    public EnemyWrapper(IReadBody monster){
        this.isDead = monster.isDead();
        this.dialogue = "temporary"; //monster.getDialogue().toString(); //TODO (klas)
        this.body = new BodyWrapper(monster);
    }
    @XmlElement(name="isDead")
    public boolean getIsDead(){
        return isDead;
    }
    public void setIsDead(boolean isDead){this.isDead = isDead;}
    @XmlElement(name="Dialogue")
    public String getDialogue(){
        return dialogue;
    }
    public String toString(){
        String result = "";
        result += "isDead: " + isDead;
        result += "\nDialogue: " + dialogue;
        result += "\n" + body.toString();
        return result;
    }
    @XmlElement(name="Body")
    public BodyWrapper getBody(){
        return body;
    }
    public void setBody(BodyWrapper body){
        this.body = body;
    }

}
