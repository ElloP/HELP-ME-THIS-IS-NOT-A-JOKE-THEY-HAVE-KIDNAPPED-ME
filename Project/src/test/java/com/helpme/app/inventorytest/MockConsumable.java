package com.helpme.app.inventorytest;

import com.helpme.app.world.item.IConsumable;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemVisitor;
import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-19.
 */
public class MockConsumable implements IConsumable {
    @Override
    public String readName() {
        return null;
    }

    @Override
    public void addStack(int amount) {

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
    public IItem clone() {
        return null;
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return null;
    }
}
