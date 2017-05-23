package com.helpme.app.model.item.concrete;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IItemVisitor;
import com.helpme.app.model.item.IKey;

import java.util.Objects;

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
    public IItem copy(){
        return new Key(name);
    }

    @Override
    public String readName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return o != null &&
                o instanceof Key &&
                Objects.equals(name, ((Key) o).name);
    }

    @Override
    public int hashCode(){
        return ("key" + name.hashCode()).hashCode();
    }
}
