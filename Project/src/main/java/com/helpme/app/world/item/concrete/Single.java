package com.helpme.app.world.item.concrete;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemVisitor;
import com.helpme.app.world.item.ISingle;
import com.helpme.app.world.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-18.
 */
public class Single extends Item implements ISingle {
    public Single(String name, IEffect attack, IEffect selfie) {
        super(name, attack, selfie);
    }

    @Override
    public String readName() {
        return name;
    }

    @Override
    public IItem clone() {
        return new Single(name, getAttackEffect(), getSelfieEffect());
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return null;
    }
}
