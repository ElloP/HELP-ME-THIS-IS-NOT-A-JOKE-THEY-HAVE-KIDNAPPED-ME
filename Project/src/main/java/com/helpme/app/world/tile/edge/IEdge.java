package com.helpme.app.world.tile.edge;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.tile.edge.visitor.IEdgeVisitor;


/**
 * Created by Jacob on 2017-03-30.
 */
public interface IEdge extends ITarget{
    boolean accept(IEdgeVisitor visitor);
    EdgeType getType();
    static Maybe<IEdge> createEdge(EdgeType edge){
        switch (edge){
            case WALL: return Maybe.wrap(new Wall());
            case OPENING: return Maybe.wrap(new Opening());
            default: return new Nothing<>();
        }
    }
}
