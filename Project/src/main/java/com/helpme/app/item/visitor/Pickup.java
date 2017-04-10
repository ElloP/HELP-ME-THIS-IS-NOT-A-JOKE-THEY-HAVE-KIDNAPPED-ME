package com.helpme.app.item.visitor;

import com.helpme.app.character.IInventory;
import com.helpme.app.item.Consumable;
import com.helpme.app.item.Item;
import com.helpme.app.item.Key;

/**
 * Created by kopa on 2017-04-10.
 */
public class Pickup implements IItemVisitor {
    private final IInventory inventory;

    public Pickup(IInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean visit(Consumable consumable) {
        if(inventory.hasItem(consumable)){
            return inventory.addStack(consumable, consumable.getStacks());
        }
        return inventory.addItem(consumable);
    }

    @Override
    public boolean visit(Item item) {
        return inventory.addItem(item);
    }

    @Override
    public boolean visit(Key key) {
        inventory.addKey(key);
        return true;
    }
}
