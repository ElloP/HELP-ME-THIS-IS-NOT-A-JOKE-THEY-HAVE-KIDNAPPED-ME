package com.helpme.app.savetest;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.concrete.Item;
import com.helpme.app.world.item.IItemVisitor;

/**
 * Created by kopa on 2017-05-15.
 */
public class MockItem implements IItem {
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
        return new MockItem(name);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return null;
    }
}
