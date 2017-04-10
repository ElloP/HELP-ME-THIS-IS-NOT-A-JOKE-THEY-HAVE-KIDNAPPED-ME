package com.helpme.app.item;

import com.helpme.app.item.effect.IEffect;
import com.helpme.app.item.visitor.IItemVisitor;

/**
 * Created by kopa on 2017-04-09.
 */
public class Consumable extends Item {
    private int stacks;

    public Consumable(String name, int stacks, IEffect attackEffect, IEffect selfieEffect) {
        super(name, attackEffect, selfieEffect);
        setStacks(stacks);
    }

    private void setStacks(int stacks){
        this.stacks = stacks <= 0 ? 0 : stacks;
    }

    public boolean addStack(){
        stacks++;
        return true;
    }

    public boolean removeStack(){
        stacks--;
        return true;
    }

    public boolean isEmpty(){
        return stacks <= 0;
    }

    @Override
    public String toString(){
        return "Consumable: " + name;
    }

    @Override
    public boolean accept(IItemVisitor visitor) {
        return visitor.visit(this);
    }
}
