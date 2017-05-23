package com.helpme.app.model.tile.edge;

import com.helpme.app.model.item.effect.ITarget;
import com.helpme.app.utils.interfaces.ICopyable;


/**
 * Created by Jacob on 2017-03-30.
 */
public interface IEdge extends ITarget, ICopyable<IEdge>{
    <T> T accept(IEdgeVisitor<T> visitor);
}
