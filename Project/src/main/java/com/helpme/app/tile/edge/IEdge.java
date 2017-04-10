package com.helpme.app.tile.edge;

import com.helpme.app.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public interface IEdge {
    boolean accept(IEdgeVisitor visitor);
}
