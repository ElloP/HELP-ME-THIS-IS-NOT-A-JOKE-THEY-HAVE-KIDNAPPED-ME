package com.helpme.app.game.model.body.concrete.visitor;

import com.helpme.app.game.model.item.IConsumable;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.IKey;
import com.helpme.app.game.model.item.ISingle;

/**
 * Created by kopa on 2017-04-10.
 */
public final class Stack implements IItemVisitor<Boolean> {
    private final int amount;

    public Stack(int amount) {
        this.amount = Math.max(amount, 0);
    }

    @Override
    public Boolean visit(IConsumable consumable) {
        consumable.addStacks(amount);
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
