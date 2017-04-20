package com.helpme.app.world.tile.edge;

import com.helpme.app.world.character.ITarget;
import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;

/**
 * Created by Jacob on 2017-03-30.
 */
public interface IEdge extends ITarget{
    boolean accept(IEdgeVisitor visitor);
}
