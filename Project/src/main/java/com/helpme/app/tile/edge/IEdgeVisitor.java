package com.helpme.app.tile.edge;

import com.helpme.app.character.Monster;

/**
 * Created by kopa on 2017-04-08.
 */
public interface IEdgeVisitor {
    boolean visit(Door door);
    boolean visit(Wall wall);
    boolean visit(Opening opening);
}
