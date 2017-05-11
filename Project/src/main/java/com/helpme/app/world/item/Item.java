package com.helpme.app.world.item;

import com.helpme.app.world.item.effect.IEffect;
import com.helpme.app.world.item.visitor.IItemVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Item implements IItem {
    private IEffect attackEffect;
    private IEffect selfieEffect;

    protected String name;

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
        if(o == null) {
            return false;
        }
        return name == ((Item) o).name;
    }

    @Override
    public IItem clone(){
        return new Item(name, attackEffect, selfieEffect);
    }

    @Override
    public String toString(){
        return "Item: " + name;
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String readName() {
        return name;
    }
}
