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
public class PlayerWrapper implements IPlayerWrapper{
    private float maxHitpoints;
    private float currentHitpoints;
    private InventoryWrapper inventory;
    private Vector2f position;
    private Vector2f direction;
    private float x;
    private float y;
    private float dirX;
    private float dirY;

    public PlayerWrapper(){}


    public PlayerWrapper(IReadMonster monster){
        this.maxHitpoints = monster.readMaxHp();
        this.currentHitpoints = monster.readCurrentHp();
        this.inventory = new InventoryWrapper(monster.readInventory());
        this.position = monster.readPosition();
        this.x = position.x;
        this.y = position.y;

        this.direction = monster.readDirection();
        this.dirX = direction.x;
        this.dirY = direction.y;
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
    @XmlElement(name="x")
    public float getX(){
        return x;
    }
    public void setX(float x){
        this.x = x;
    }
    @XmlElement(name="y")
    public float getY(){
        return y;
    }
    public void setY(float y){
        this.y = y;
    }
    @XmlElement(name="DirX")
    public float getDirX() {
        return dirX;
    }
    public void setDirX(float dirX) {
        this.dirX = dirX;
    }
    @XmlElement(name="DirY")
    public float getDirY() {
        return dirY;
    }
    public void setDirY(float dirY) {
        this.dirY = dirY;
    }

    @Override
    public String toString(){
        String result = "";
        result += "maxHitpoints: " + maxHitpoints;
        result += "\ncurrHitpoints: " + currentHitpoints;
        result += inventory.toString();
        result += "\nposition: (" + x + ", " + y + ")";
        result += "\ndirection: (" + dirX + ", " + dirY + ")";
        return result;
    }
}
