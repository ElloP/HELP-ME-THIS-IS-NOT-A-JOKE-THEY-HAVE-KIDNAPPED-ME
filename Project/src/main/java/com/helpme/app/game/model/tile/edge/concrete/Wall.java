package com.helpme.app.game.model.tile.edge.concrete;

import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.game.model.tile.edge.IEdgeVisitor;
import com.helpme.app.game.model.tile.edge.IWall;

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
        return;
    }

    @Override
    public void heal(float amount) {
        return;
    }

    @Override
    public IEdge copy() {
        return new Wall();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Wall;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
