package com.helpme.app.tile.edge.visitor;

import com.helpme.app.character.Monster;
import com.helpme.app.tile.edge.Door;
import com.helpme.app.tile.edge.Opening;
import com.helpme.app.tile.edge.Wall;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IEdgeVisitor {
    boolean visit(Door door);
    boolean visit(Wall wall);
    boolean visit(Opening opening);
}
