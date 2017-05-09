package com.helpme.app.world.tile.edge;

import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Wall implements IEdge {

    public static EdgeType type = EdgeType.WALL;

    public boolean isDead(){ return false; }

    @Override
    public boolean accept(IEdgeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public EdgeType getType() {
        return this.type;
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
