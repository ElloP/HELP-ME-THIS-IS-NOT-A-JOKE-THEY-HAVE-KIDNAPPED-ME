package com.helpme.app.game.model.body.concrete.visitor;

import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.item.IConsumable;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.IKey;
import com.helpme.app.game.model.item.ISingle;

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
