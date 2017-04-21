package com.helpme.app.world.item;

import com.helpme.app.world.item.visitor.IItemVisitor;

/**
 * Created by kopa on 2017-04-10.
 */
public class Key extends Item implements IItem {

    public Key(String name) {
        super(name);
    }

    @Override
    public boolean accept(IItemVisitor visitor) {
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
}
