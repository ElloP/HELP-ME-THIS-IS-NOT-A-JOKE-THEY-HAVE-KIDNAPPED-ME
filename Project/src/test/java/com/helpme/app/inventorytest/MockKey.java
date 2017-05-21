package com.helpme.app.inventorytest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;
import com.helpme.app.model.item.IKey;

/**
 * Created by kopa on 2017-05-19.
 */
public class MockKey implements IKey {
    String name;

    public MockKey(String name){
        this.name = name;
    }

    @Override
    public String readName() {
        return name;
    }

    @Override
    public IItem clone() {
        return new MockKey(name);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
