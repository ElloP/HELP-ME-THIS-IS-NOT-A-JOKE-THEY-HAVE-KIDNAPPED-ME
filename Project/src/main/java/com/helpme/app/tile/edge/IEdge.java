package com.helpme.app.tile.edge;

import com.helpme.app.character.Monster;
import com.helpme.app.item.Item;

/**
 * Created by Jacob on 2017-03-30.
 */
public interface IEdge {
    boolean accept(IEdgeVisitor visitor);
}
