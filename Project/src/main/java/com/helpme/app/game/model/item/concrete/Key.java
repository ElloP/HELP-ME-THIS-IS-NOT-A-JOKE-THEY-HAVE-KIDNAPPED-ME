package com.helpme.app.game.model.item.concrete;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IItemVisitor;
import com.helpme.app.game.model.item.IKey;

/**
 * Created by kopa on 2017-04-10.
 */
public class Key extends Item implements IKey {

    public Key(String name) {
        super(name, null, null);
    }

    @Override
    public <T> T accept(IItemVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Key: " + name;
    }

    @Override
    public IItem copy() {
        return new Key(name);
    }

    @Override
    public boolean equals(Object o) {
        return o != null &&
                o instanceof Key &&
                ((Key) o).readName().equals(readName());
    }

    @Override
    public int hashCode() {
        return ("key" + name.hashCode()).hashCode();
    }
}
