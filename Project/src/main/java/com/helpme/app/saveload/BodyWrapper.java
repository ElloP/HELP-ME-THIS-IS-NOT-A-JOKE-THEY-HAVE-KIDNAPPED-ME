package com.helpme.app.saveload;

import com.helpme.app.world.body.concrete.Body;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by Klas on 2017-04-29.
 */

@XmlRootElement(name="Body")
public class BodyWrapper implements ILoadable<IBody>{
    private Vector2Wrapper hitpointsWrapper;
    private InventoryWrapper inventoryWrapper;
    private Vector2Wrapper positionWrapper;
    private Vector2Wrapper directionWrapper;

    private boolean dead;

    public BodyWrapper(){}



    public BodyWrapper(IReadBody body){
        this.dead = body.isDead();
        this.hitpointsWrapper = new Vector2Wrapper(body.readHitpoints());
        this.inventoryWrapper = new InventoryWrapper(body.readInventory());
        this.positionWrapper = new Vector2Wrapper(body.readPosition());
        this.directionWrapper = new Vector2Wrapper(body.readDirection());
    }

    @XmlElement(name="dead")

    public boolean getDead() {
        return dead;
    }
    public void setDead(boolean dead){
        this.dead = dead;
    }
    @XmlElement(name="hitpoints")
    public Vector2Wrapper getHitpoints() {
        return this.hitpointsWrapper;
    }
    public void setHitpoints(Vector2Wrapper hitpoints) {
        this.hitpointsWrapper = hitpoints;
    }
    @XmlElement(name="inventory")
    public InventoryWrapper getInventory() {
        return inventoryWrapper;
    }
    public void setInventory(InventoryWrapper inventory) {
        this.inventoryWrapper = inventory;
    }

    @XmlElement(name="position")
    public Vector2Wrapper getPosition(){
        return this.positionWrapper;
    }
    @XmlElement(name="direction")
    public Vector2Wrapper getDirection(){
        return this.directionWrapper;
    }
    public void setPosition(Vector2Wrapper vec){
        this.positionWrapper = vec;
    }
    public void setDirection(Vector2Wrapper dir){
        this.directionWrapper = dir;
    }

    @Override
    public String toString(){
        String result = "";
        result += "maxHitpoints: " + hitpointsWrapper.getX();
        result += "\ncurrHitpoints: " + hitpointsWrapper.getY();
        result += inventoryWrapper.toString();
        result += "\nposition: " + positionWrapper;
        result += "\ndirection: " + directionWrapper;
        return result;
    }

    @Override
    public IBody getObject() {
        return new Body(inventoryWrapper.getObject(), positionWrapper.getObject(), directionWrapper.getObject(), hitpointsWrapper.getObject());
    }
}
