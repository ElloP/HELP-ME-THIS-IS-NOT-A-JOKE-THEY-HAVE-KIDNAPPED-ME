package com.helpme.app.bodytest;

import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.model.tile.edge.IEdgeVisitor;
import com.helpme.app.model.tile.edge.IOpening;

/**
 * Created by kopa on 2017-05-24.
 */
public class MockOpening implements IOpening {
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
    public <T> T accept(IEdgeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
