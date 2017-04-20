package com.helpme.app.world.tile.edge;

import com.helpme.app.world.item.*;
import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Door implements IEdge {
    private boolean locked;
    private IItem key;

    public Door(boolean locked, IItem key) {
        this.locked = locked;
        this.key = key == null ? IKeyFactory.skeletonKey() : key;
    }

    public boolean isDead(){ return false; }

    @Override
    public boolean accept(IEdgeVisitor visitor) {
        return visitor.visit(this);
    }

    public boolean isLocked() {
        return locked;
    }

    public IItem getKey() {
        return key.clone();
    }

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

