package com.helpme.app.world.item.visitor;

import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.item.Consumable;
import com.helpme.app.world.item.Item;
import com.helpme.app.world.item.Key;

/**
 * Created by kopa on 2017-04-10.
 */
public final class Pickup implements IItemVisitor<Boolean> {
    private final IInventory inventory;

    public Pickup(IInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Boolean visit(Consumable consumable) {
        if(inventory.hasItem(consumable)){
            return inventory.addStack(consumable, consumable.getStacks());
        }
        return inventory.addItem(consumable);
    }

    @Override
    public Boolean visit(Item item) {
        return inventory.addItem(item);
    }

    @Override
    public Boolean visit(Key key) {
        inventory.addKey(key);
        return true;
    }
}
