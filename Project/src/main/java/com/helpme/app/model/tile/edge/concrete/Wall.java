package com.helpme.app.model.tile.edge.concrete;

import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.model.tile.edge.IEdgeVisitor;
import com.helpme.app.model.tile.edge.IWall;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Wall implements IWall {

    @Override
    public boolean isDead(){ return false; }

    @Override
    public <T> T accept(IEdgeVisitor<T> visitor) {
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

    @Override
    public IEdge copy() {
        return new Wall();
    }
}
