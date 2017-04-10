package com.helpme.app.tile.edge;
import com.helpme.app.item.IItem;
import com.helpme.app.item.Item;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Door implements IEdge {
    private boolean locked;
    private IItem key;

    public Door(boolean locked, IItem key) {
        this.locked = locked;
        this.key = key == null ? new Item("Skeleton Key") : key;
    }

    @Override
    public boolean accept(IEdgeVisitor visitor) {
        return visitor.visit(this);
    }

    public boolean isLocked() {
        return locked;
    }

    public Item getKey() {
        return key.clone();
    }

    public void unlock() {
        locked = false;
    }
}
