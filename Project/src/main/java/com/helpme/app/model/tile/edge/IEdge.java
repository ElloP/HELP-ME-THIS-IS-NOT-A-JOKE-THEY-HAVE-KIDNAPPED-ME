package com.helpme.app.model.tile.edge;

import com.helpme.app.model.item.effect.ITarget;


/**
 * Created by Jacob on 2017-03-30.
 */
public interface IEdge extends ITarget{
    <T> T accept(IEdgeVisitor<T> visitor);
}
