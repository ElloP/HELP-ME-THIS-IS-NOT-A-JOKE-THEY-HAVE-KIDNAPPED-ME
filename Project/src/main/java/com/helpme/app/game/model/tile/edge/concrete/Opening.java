package com.helpme.app.game.model.tile.edge.concrete;

import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.IEdgeVisitor;
import com.helpme.app.game.model.tile.edge.IOpening;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Opening implements IOpening {

    @Override
    public boolean isDead(){ return false; }

    @Override
    public <T> T accept(IEdgeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void damage(float amount) {
        return; //TODO (klas)
    }

    @Override
    public void heal(float amount) {
        return; //TODO (klas)
    }

    @Override
    public IEdge copy() {
        return new Opening();
    }
}
