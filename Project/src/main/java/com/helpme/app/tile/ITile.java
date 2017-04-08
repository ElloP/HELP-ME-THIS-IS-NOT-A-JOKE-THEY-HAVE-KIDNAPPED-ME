package com.helpme.app.tile;

import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

/**
 * Created by kopa on 2017-04-08.
 */
public interface ITile {
    IEdge getEdge(Vector2f direction);
    void setEdge(IEdge edge, Vector2f direction);
}
