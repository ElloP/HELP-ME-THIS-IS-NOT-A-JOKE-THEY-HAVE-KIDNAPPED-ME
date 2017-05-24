package com.helpme.app.leveltest;

import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.model.tile.edge.IEdgeVisitor;

/**
 * Created by kopa on 2017-05-15.
 */
public class MockEdge implements IEdge{
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
    public <T> T accept(IEdgeVisitor<T> visitor) {
        return null;
    }

    @Override
    public IEdge copy() {
        return null;
    }
}
