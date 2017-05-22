package com.helpme.app.model.item.concrete;

import com.helpme.app.model.item.effect.IEffect;

import java.util.Objects;

/**
 * Created by Jacob on 2017-03-30.
 */
public abstract class Item {
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
        return o != null && o instanceof Item && Objects.equals(name, ((Item) o).name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
        return "Item: " + name;
    }
}