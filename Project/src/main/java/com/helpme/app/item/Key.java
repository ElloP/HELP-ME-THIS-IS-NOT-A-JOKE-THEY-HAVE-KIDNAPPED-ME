package com.helpme.app.item;

import com.helpme.app.item.visitor.IItemVisitor;

/**
 * Created by kopa on 2017-04-10.
 */
public class Key extends Item implements IKey {

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
    public IKey copy() {
        return new Key(name);
    }
}
