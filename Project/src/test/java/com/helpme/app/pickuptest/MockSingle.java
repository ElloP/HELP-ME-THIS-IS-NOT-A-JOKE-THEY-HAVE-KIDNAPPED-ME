package com.helpme.app.pickuptest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;
import com.helpme.app.model.item.ISingle;
import com.helpme.app.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockSingle implements ISingle {
    @Override
    public String readName() {
        return null;
    }

    @Override
    public IEffect getAttackEffect() {
        return null;
    }

    @Override
    public IEffect getSelfieEffect() {
        return null;
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
