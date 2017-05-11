package com.helpme.app.world.tile.edge.visitor;

import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.world.tile.edge.Opening;
import com.helpme.app.world.tile.edge.Wall;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IEdgeVisitor<T> {
    T visit(Door door);
    T visit(Wall wall);
    T visit(Opening opening);
}
