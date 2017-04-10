package com.helpme.app.item;

import com.helpme.app.character.IStats;
import com.helpme.app.item.effect.IEffect;
import com.helpme.app.item.visitor.IItemVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Item implements IItem {
    private String name;
    private IEffect attackEffect;
    private IEffect selfieEffect;

    public Item(String name) {
        this.name = name;
        this.attackEffect = stats -> {};
        this.selfieEffect = stats -> {};
    }

    public Item(String name, IEffect attackEffect, IEffect selfieEffect) {
        this.name = name;
        this.attackEffect = attackEffect;
        this.selfieEffect = selfieEffect;
    }

    public IEffect getAttackEffect(){
        return attackEffect;
    }

    public IEffect getSelfieEffect(){
        return selfieEffect;
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
    public boolean accept(IItemVisitor visitor) {
        return visitor.visit(this);
    }
}
