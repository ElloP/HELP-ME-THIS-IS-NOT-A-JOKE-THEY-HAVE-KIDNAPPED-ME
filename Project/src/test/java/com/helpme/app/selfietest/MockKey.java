package com.helpme.app.selfietest;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemVisitor;
import com.helpme.app.world.item.IKey;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockKey implements IKey {
    @Override
    public String readName() {
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
