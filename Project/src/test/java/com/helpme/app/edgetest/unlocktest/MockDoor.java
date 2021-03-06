package com.helpme.app.edgetest.unlocktest;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.tile.edge.IDoor;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.IEdgeVisitor;

/**
 * Created by kopa on 2017-05-25.
 */
public class MockDoor implements IDoor {
    boolean locked;

    @Override
    public IEdge copy() {
        return null;
    }

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
    public boolean isLocked() {
        return locked;
    }

    @Override
    public IItem getKey() {
        return null;
    }

    @Override
    public void unlock() {
        locked = true;
    }

    @Override
    public <T> T accept(IEdgeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
