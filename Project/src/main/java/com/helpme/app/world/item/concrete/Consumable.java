package com.helpme.app.world.item.concrete;

import com.helpme.app.world.item.IConsumable;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemVisitor;
import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-04-09.
 */
public class Consumable extends Item implements IConsumable {
    private int stacks;

    public Consumable(String name, int stacks, IEffect attackEffect, IEffect selfieEffect) {
        super(name, attackEffect, selfieEffect);
        setStacks(stacks);
    }

    private void setStacks(int stacks) {
        this.stacks = stacks <= 0 ? 0 : stacks;
    }

    @Override
    public void addStack(int amount) {
        stacks += amount;
    }

    @Override
    public void removeStack() {
        stacks--;
    }

    @Override
    public boolean isEmpty() {
        return stacks <= 0;
    }

    @Override
    public int getStacks() {
        return stacks;
    }

    @Override
    public String toString() {
        return "Consumable: " + name;
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public IItem clone(){
        return new Consumable(name, stacks, getAttackEffect(), getSelfieEffect());
    }

    @Override
    public String readName() {
        return name;
    }
}
