package com.helpme.app.world.tile.edge;

import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Opening implements IEdge {

    public static EdgeType type = EdgeType.OPENING;
    public boolean isDead(){ return false; }

    @Override
    public <T> T accept(IEdgeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public EdgeType getType() {
        return this.type;
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
