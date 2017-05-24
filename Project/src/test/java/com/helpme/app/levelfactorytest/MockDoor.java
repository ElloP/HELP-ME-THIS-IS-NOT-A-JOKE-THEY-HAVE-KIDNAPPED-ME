package com.helpme.app.levelfactorytest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.tile.edge.IDoor;
import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.model.tile.edge.IEdgeVisitor;

/**
 * Created by kopa on 2017-05-24.
 */
public class MockDoor implements IDoor {
    @Override
    public void damage(float amount) {

    }

    @Override
    public void heal(float amount) {

    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public IEdge copy() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public IItem getKey() {
        return null;
    }

    @Override
    public void unlock() {

    }

    @Override
    public <T> T accept(IEdgeVisitor<T> visitor) {
        return null;
    }
}
