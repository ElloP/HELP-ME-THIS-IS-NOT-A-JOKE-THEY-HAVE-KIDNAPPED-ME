package com.helpme.app.model.tile.edge.concrete;

import com.helpme.app.model.item.*;
import com.helpme.app.model.tile.edge.IDoor;
import com.helpme.app.model.tile.edge.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Door implements IDoor {
    private boolean locked;
    private IItem key;

    public Door(boolean locked, IItem key) {
        this.locked = locked;
        this.key = key;
    }

    public boolean isDead(){ return false; }

    @Override
    public <T> T accept(IEdgeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public IItem getKey() {
        return key.copy();
    }

    @Override
    public void unlock() {
        locked = false;
    }

    @Override
    public void damage(float amount) {
        return; //TODO (klas)
    }

    @Override
    public void heal(float amount) {
        return; //TODO (klas)
    }
}

