package com.helpme.app.game.model.item.concrete;

import com.helpme.app.game.model.item.IConsumable;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.effect.IEffect;

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
    public void addStacks(int amount) {
        stacks += Math.max(amount, 0);
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
    public IItem copy() {
        return new Consumable(name, stacks, getAttackEffect(), getSelfieEffect());
    }

    @Override
    public boolean equals(Object o) {
        return o != null &&
                o instanceof Consumable &&
                ((Consumable) o).stacks == stacks &&
                super.equals(o);
    }

    @Override
    public int hashCode() {
        return ("consumable" + name).hashCode();
    }
}
