package com.helpme.app.world.tile.edge;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IEdgeVisitor<T> {
    T visit(IDoor door);
    T visit(IWall wall);
    T visit(IOpening opening);
}
