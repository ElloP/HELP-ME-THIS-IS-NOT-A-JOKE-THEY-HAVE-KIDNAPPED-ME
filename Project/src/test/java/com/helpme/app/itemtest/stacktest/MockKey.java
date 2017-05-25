package com.helpme.app.itemtest.stacktest;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.IKey;

/**
 * Created by kopa on 2017-05-23.
 */
public class MockKey implements IKey {
    @Override
    public String readName() {
        return null;
    }

    @Override
    public IItem copy() {
        return null;
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
