package com.helpme.app.tiletest;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemVisitor;

/**
 * Created by kopa on 2017-05-13.
 */
public final class MockItem implements IItem {
    private String name;

    public MockItem(String name) {
        this.name = name;
    }

    @Override
    public String readName() {
        return name;
    }

    @Override
    public IItem clone() {
        return null;
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return null;
    }
}
