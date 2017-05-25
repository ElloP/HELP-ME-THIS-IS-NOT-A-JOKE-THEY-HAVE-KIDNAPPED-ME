package com.helpme.app.itemtest.selfietest;

import com.helpme.app.model.item.IConsumable;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;
import com.helpme.app.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockConsumable implements IConsumable {
    int stacks;
    boolean empty;

    @Override
    public String readName() {
        return null;
    }

    @Override
    public void addStacks(int amount) {

    }

    @Override
    public void removeStack() {
        stacks--;
    }

    @Override
    public boolean isEmpty() {
        return empty;
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
        return t -> t.heal(0);
    }

    @Override
    public IItem copy() {
        return null;
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
