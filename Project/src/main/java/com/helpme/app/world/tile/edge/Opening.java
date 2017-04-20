package com.helpme.app.world.tile.edge;

import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Opening implements IEdge {

    @Override
    public boolean accept(IEdgeVisitor visitor) {
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
}