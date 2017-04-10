package com.helpme.app.tile.edge;

import com.helpme.app.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Opening implements IEdge {

    @Override
    public boolean accept(IEdgeVisitor visitor) {
        return visitor.visit(this);
    }
}
