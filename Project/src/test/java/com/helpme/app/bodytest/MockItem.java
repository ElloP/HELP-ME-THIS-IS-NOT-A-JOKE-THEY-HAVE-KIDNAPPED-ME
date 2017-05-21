package com.helpme.app.bodytest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;
import com.helpme.app.model.item.ISingle;
import com.helpme.app.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-11.
 */
public class MockItem implements ISingle{
    private IEffect attack;
    private IEffect selfie;

    public MockItem(IEffect attack, IEffect selfie){
        this.attack = attack;
        this.selfie = selfie;
    }

    @Override
    public String readName() {
        return null;
    }

    @Override
    public IEffect getAttackEffect() {
        return attack;
    }

    @Override
    public IEffect getSelfieEffect() {
        return selfie;
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
