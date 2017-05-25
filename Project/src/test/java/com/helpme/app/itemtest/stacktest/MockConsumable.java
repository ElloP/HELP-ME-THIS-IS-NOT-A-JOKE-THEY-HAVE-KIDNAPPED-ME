package com.helpme.app.itemtest.stacktest;

import com.helpme.app.model.item.IConsumable;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;
import com.helpme.app.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-23.
 */
public class MockConsumable implements IConsumable {
    int stacks;

    @Override
    public String readName() {
        return null;
    }

    @Override
    public IItem copy() {
        return null;
    }

    @Override
    public void addStacks(int amount) {
        stacks+=amount;
    }

    @Override
    public void removeStack() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getStacks() {
        return 0;
    }

    @Override
    public IEffect getAttackEffect() {
        return null;
    }

    @Override
    public IEffect getSelfieEffect() {
        return null;
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
