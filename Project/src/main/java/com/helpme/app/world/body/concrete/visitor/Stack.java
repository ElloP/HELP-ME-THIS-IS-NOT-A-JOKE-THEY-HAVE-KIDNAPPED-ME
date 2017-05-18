package com.helpme.app.world.body.concrete.visitor;

import com.helpme.app.world.item.*;

/**
 * Created by kopa on 2017-04-10.
 */
public final class Stack implements IItemVisitor {
    private final int amount;

    public Stack(int amount){
        this.amount = amount;
    }

    @Override
    public Boolean visit(IConsumable consumable) {
        consumable.addStack(amount);
        return true;
    }

    @Override
    public Boolean visit(ISingle item) {
        return false;
    }

    @Override
    public Boolean visit(IKey key) {
        return false;
    }
}
