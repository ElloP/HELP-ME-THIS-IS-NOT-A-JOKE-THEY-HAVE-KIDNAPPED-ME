package com.helpme.app.world.tile.edge.visitor;

import com.helpme.app.world.tile.edge.Door;
import com.helpme.app.world.tile.edge.Opening;
import com.helpme.app.world.tile.edge.Wall;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IEdgeVisitor {
    boolean visit(Door door);
    boolean visit(Wall wall);
    boolean visit(Opening opening);
}
