package com.helpme.app.world.body.concrete.visitor;

import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.item.*;

/**
 * Created by kopa on 2017-04-10.
 */
public final class Pickup implements IItemVisitor<Boolean> {
    private final IInventory inventory;

    public Pickup(IInventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Boolean visit(IConsumable consumable) {
        if (consumable.getStacks() <= 0) {
            return false;
        } else if (inventory.hasItem(consumable)) {
            return inventory.addStack(consumable, consumable.getStacks());
        }
        return inventory.addItem(consumable);
    }

    @Override
    public Boolean visit(ISingle item) {
        return inventory.addItem(item);
    }

    @Override
    public Boolean visit(IKey key) {
        inventory.addKey(key);
        return true;
    }
}
