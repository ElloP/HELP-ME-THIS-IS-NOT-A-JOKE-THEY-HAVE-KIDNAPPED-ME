package com.helpme.app.world.item.concrete;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IItemVisitor;
import com.helpme.app.world.item.IKey;

/**
 * Created by kopa on 2017-04-10.
 */
public class Key extends Item implements IKey {

    public Key(String name) {
        super(name);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString(){
        return "Key: " + name;
    }

    @Override
    public IItem clone(){
        return new Key(name);
    }

    @Override
    public String readName() {
        return name;
    }
}