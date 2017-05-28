package com.helpme.app.game.saveload;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.body.concrete.BodyFactory;
import com.helpme.app.utils.interfaces.ILoadable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Created by Klas on 2017-04-29.
 */

@XmlRootElement(name="Body")
public class BodyWrapper implements ILoadable<IBody> {
    @XmlElement(name = "hitpoints")
    private Vector2Wrapper hitpointsWrapper;

    @XmlElement(name = "inventory")
    private InventoryWrapper inventoryWrapper;

    @XmlElement(name = "position")
    private Vector2Wrapper positionWrapper;

    @XmlElement(name = "direction")
    private Vector2Wrapper directionWrapper;

    @XmlElement(name="dead")
    private boolean dead;

    public BodyWrapper(){}



    public BodyWrapper(IReadBody body){
        this.dead = body.isDead();
        this.hitpointsWrapper = new Vector2Wrapper(body.readHitpoints());
        this.inventoryWrapper = new InventoryWrapper(body.readInventory());
        this.positionWrapper = new Vector2Wrapper(body.readPosition());
        this.directionWrapper = new Vector2Wrapper(body.readDirection());
    }

    @Override
    public IBody getObject() {
        return BodyFactory.createBody(inventoryWrapper.getObject(), positionWrapper.getObject(), directionWrapper.getObject(), hitpointsWrapper.getObject(), dead);
    }
}
