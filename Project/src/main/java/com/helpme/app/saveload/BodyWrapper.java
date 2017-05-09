package com.helpme.app.saveload;

import com.helpme.app.world.character.Body;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import javax.xml.bind.annotation.XmlElement;


/**
 * Created by Klas on 2017-04-29.
 */
public class BodyWrapper implements ILoadable<IBody>{
    private Vector2Wrapper hitpoints;
    private InventoryWrapper inventory;
    private Vector2Wrapper position;
    private Vector2Wrapper direction;

    public BodyWrapper(){}


    public BodyWrapper(IReadBody monster){
        this.hitpoints = new Vector2Wrapper(monster.readHitpoints());
        this.inventory = new InventoryWrapper(monster.readInventory());
        this.position = new Vector2Wrapper(monster.readPosition());
        this.direction = new Vector2Wrapper(monster.readDirection());
    }
    @XmlElement(name="Hitpoints")
    public Vector2Wrapper getHitpoints() {
        return this.hitpoints;
    }
    public void setHitpoints(Vector2Wrapper hitpoints) {
        this.hitpoints = hitpoints;
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
        result += "maxHitpoints: " + hitpoints.getX();
        result += "\ncurrHitpoints: " + hitpoints.getY();
        result += inventory.toString();
        result += "\nposition: " + position;
        result += "\ndirection: " + direction;
        return result;
    }

    @Override
    public IBody getObject() {
        return new Body(inventory.getObject(),position.getObject(),direction.getObject(),hitpoints.getObject());
    }
}
