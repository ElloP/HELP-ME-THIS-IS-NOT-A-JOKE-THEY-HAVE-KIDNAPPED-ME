package com.helpme.app.saveload;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.item.IReadItem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Klas on 2017-04-29.
 */
@XmlRootElement(name = "Player")
public class PlayerWrapper {
    private float maxHitpoints;
    private float currentHitpoints;
    private InventoryWrapper inventory;
    private Vector2Wrapper position;
    private Vector2Wrapper direction;

    public PlayerWrapper(){}


    public PlayerWrapper(IReadMonster monster){
        this.maxHitpoints = monster.readMaxHp();
        this.currentHitpoints = monster.readCurrentHp();
        this.inventory = new InventoryWrapper(monster.readInventory());
        this.position = new Vector2Wrapper(monster.readPosition());
        this.direction = new Vector2Wrapper(monster.readDirection());
    }
    @XmlElement(name="maxHp")
    public float getMaxHitpoints() {
        return maxHitpoints;
    }
    public void setMaxHitpoints(float maxHitpoints) {
        this.maxHitpoints = maxHitpoints;
    }
    @XmlElement(name="currHp")
    public float getCurrentHitpoints() {
        return currentHitpoints;
    }
    public void setCurrentHitpoints(float currentHitpoints) {
        this.currentHitpoints = currentHitpoints;
    }
    @XmlElement(name="inventory")
    public InventoryWrapper getInventory() {
        return inventory;
    }
    public void setInventory(InventoryWrapper inventory) {
        this.inventory = inventory;
    }
    @XmlElement(name="position")
    public Vector2Wrapper getPosition(){
        return this.position;
    }
    @XmlElement(name="direction")
    public Vector2Wrapper getDirection(){
        return this.direction;
    }
    public void setPosition(Vector2Wrapper vec){
        this.position = vec;
    }
    public void setDirection(Vector2Wrapper dir){
        this.direction = dir;
    }

    @Override
    public String toString(){
        String result = "";
        result += "maxHitpoints: " + maxHitpoints;
        result += "\ncurrHitpoints: " + currentHitpoints;
        result += inventory.toString();
        result += "\nposition: " + position;
        result += "\ndirection: " + direction;
        return result;
    }
}
