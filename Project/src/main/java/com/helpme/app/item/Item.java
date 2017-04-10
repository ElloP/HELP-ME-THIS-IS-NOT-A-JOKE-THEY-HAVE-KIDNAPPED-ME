package com.helpme.app.item;

import com.helpme.app.character.IStats;
import com.helpme.app.item.effect.IEffect;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Item implements IItem {
    private String name;
    private IEffect attackEffect;
    private IEffect selfieEffect;

    protected int stacks = 1;

    public Item(String name, IEffect attackEffect, IEffect selfieEffect) {
        this.name = name;
        this.attackEffect = attackEffect;
        this.selfieEffect = selfieEffect;
    }

    @Override
    public boolean equals(Object o) {
        return name == ((Item) o).name;
    }

    @Override
    public Item clone(){
        return new Item(name, attackEffect, selfieEffect);
    }

    @Override
    public void attack(IStats stats) {
        attackEffect.apply(stats);
    }

    @Override
    public void selfie(IStats stats) {
        selfieEffect.apply(stats);
    }

    @Override
    public boolean addStack() {
        return false;
    }

    @Override
    public boolean removeStack() {
        return false;
    }


}
