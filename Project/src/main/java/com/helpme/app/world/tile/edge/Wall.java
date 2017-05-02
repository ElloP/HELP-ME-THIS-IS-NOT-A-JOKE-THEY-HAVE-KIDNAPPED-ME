package com.helpme.app.world.tile.edge;

import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Wall implements IEdge {

    public boolean isDead(){ return false; }

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
