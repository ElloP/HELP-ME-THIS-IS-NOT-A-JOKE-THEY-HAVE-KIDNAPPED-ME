package com.helpme.app.leveltest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;

/**
 * Created by kopa on 2017-05-23.
 */
public class MockItem implements IItem {
    String name;

    public MockItem(String name){
        this.name = name;
    }

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
        return null;
    }
}
