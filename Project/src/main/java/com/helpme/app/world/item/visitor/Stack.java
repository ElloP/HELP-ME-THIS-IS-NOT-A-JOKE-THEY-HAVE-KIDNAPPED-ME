package com.helpme.app.world.item.visitor;

import com.helpme.app.world.item.Consumable;
import com.helpme.app.world.item.Item;
import com.helpme.app.world.item.Key;

/**
 * Created by kopa on 2017-04-10.
 */
public final class Stack implements IItemVisitor {
    private final int amount;

    public Stack(int amount){
        this.amount = amount;
    }

    @Override
    public Boolean visit(Consumable consumable) {
        consumable.addStack(amount);
        return true;
    }

    @Override
    public Boolean visit(Item item) {
        return false;
    }

    @Override
    public Boolean visit(Key key) {
        return false;
    }
}
