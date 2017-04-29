package com.helpme.app.saveload;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.item.IReadItem;

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

    public PlayerWrapper(){}

    public PlayerWrapper(IReadMonster monster){
        maxHitpoints = monster.readMaxHp();
        currentHitpoints = monster.readCurrentHp();
        inventory = new InventoryWrapper(monster.readInventory());
    }
    @XmlElement(name="maxHp")
    public float getMaxHitpoints() {
        return maxHitpoints;
    }
    @XmlElement(name="currHp")
    public float getCurrentHitpoints() {
        return currentHitpoints;
    }
    @XmlElement(name="inventory")
    public InventoryWrapper getInventory() {
        return inventory;
    }


}
