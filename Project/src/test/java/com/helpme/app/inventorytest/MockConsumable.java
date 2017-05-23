package com.helpme.app.inventorytest;

import com.helpme.app.model.item.IConsumable;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;
import com.helpme.app.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-19.
 */
public class MockConsumable implements IConsumable {
    private String name;

    public MockConsumable(String name){
        this.name = name;
    }

    @Override
    public String readName() {
        return name;
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
    public IItem copy() {
        return new MockConsumable(name);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
