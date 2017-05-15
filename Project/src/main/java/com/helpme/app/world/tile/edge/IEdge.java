package com.helpme.app.world.tile.edge;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;


/**
 * Created by Jacob on 2017-03-30.
 */
public interface IEdge extends ITarget{
    <T> T accept(IEdgeVisitor<T> visitor);
}
