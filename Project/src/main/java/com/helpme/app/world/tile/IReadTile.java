package com.helpme.app.world.tile;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.edge.EdgeType;
import com.helpme.app.world.tile.edge.IEdge;

import java.util.Map;

/**
 * Created by kopa on 2017-04-21.
 */
public interface IReadTile {
    Maybe<IReadItem[]> readItems();
    Maybe<IReadItem> readItem(int index);
    Map<Vector2f,IEdge> readEdges();
    IEdge readEdge(Vector2f direction);
}
