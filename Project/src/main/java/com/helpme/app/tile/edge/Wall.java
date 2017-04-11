package com.helpme.app.tile.edge;

import com.helpme.app.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Wall implements IEdge {

    @Override
    public boolean accept(IEdgeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public void damage(float amount) {
        System.out.println("Hit wall");
        return; //TODO (klas)
    }

    @Override
    public void heal(float amount) {
        return; //TODO (klas)
    }
}
