package com.helpme.app.leveltest;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IItemVisitor;

/**
 * Created by kopa on 2017-05-23.
 */
public class MockItem implements IItem {

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
        return null;
    }
}
