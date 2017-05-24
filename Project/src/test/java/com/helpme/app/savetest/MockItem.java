package com.helpme.app.savetest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;

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
    public IItem copy() {
        return new MockItem(name);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return null;
    }
}
