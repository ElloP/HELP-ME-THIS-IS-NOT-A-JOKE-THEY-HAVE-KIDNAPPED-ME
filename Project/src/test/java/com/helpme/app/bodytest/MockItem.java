package com.helpme.app.bodytest;

import com.helpme.app.savetest.MockTile;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemVisitor;
import com.helpme.app.world.item.ISingle;
import com.helpme.app.world.item.concrete.Item;
import com.helpme.app.world.item.concrete.Single;
import com.helpme.app.world.item.effect.IEffect;

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
