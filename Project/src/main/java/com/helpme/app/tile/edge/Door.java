package com.helpme.app.tile.edge;

import com.helpme.app.item.*;
import com.helpme.app.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Door implements IEdge {
    private boolean locked;
    private IKey key;

    public Door(boolean locked, IKey key) {
        this.locked = locked;
        this.key = key == null ? IKeyFactory.skeletonKey() : key;
    }

    @Override
    public boolean accept(IEdgeVisitor visitor) {
        return visitor.visit(this);
    }

    public boolean isLocked() {
        return locked;
    }

    public IKey getKey() {
        return key.copy();
    }

    public void unlock() {
        locked = false;
    }
}
