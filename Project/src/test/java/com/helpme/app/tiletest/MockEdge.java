package com.helpme.app.tiletest;

import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.world.tile.edge.IEdgeVisitor;

/**
 * Created by kopa on 2017-05-13.
 */
public class MockEdge implements IEdge {
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
}
