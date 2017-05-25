package com.helpme.app.edgetest.unlocktest;

import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.IEdgeVisitor;
import com.helpme.app.game.model.tile.edge.IOpening;

/**
 * Created by kopa on 2017-05-25.
 */
public class MockOpening implements IOpening {
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
    public <T> T accept(IEdgeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
