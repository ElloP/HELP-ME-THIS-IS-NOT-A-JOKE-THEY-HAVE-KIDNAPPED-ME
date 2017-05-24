package com.helpme.app.bodytest;

import com.helpme.app.model.body.concrete.visitor.Traverse;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;
import com.helpme.app.model.item.ISingle;
import com.helpme.app.model.item.effect.IEffect;

/**
 * Created by kopa on 2017-05-24.
 */
public class MockSingle implements ISingle {
    @Override
    public String readName() {
        return null;
    }

    @Override
    public IItem copy() {
        return this;
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public IEffect getAttackEffect() {
        return null;
    }

    @Override
    public IEffect getSelfieEffect() {
        return null;
    }
}
