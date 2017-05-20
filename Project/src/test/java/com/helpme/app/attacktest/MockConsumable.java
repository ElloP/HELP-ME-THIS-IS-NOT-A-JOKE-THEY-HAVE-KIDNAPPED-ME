package com.helpme.app.attacktest;

import com.helpme.app.world.item.IConsumable;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemVisitor;
import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockConsumable implements IConsumable {
    boolean empty;
    int stack;

    @Override
    public String readName() {
        return null;
    }

    @Override
    public void addStack(int amount) {
        stack++;
    }

    @Override
    public void removeStack() {
        stack--;
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
        return t -> t.damage(0);
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
        return visitor.visit(this);
    }
}
