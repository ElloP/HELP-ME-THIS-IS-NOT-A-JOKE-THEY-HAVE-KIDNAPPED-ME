package com.helpme.app.model.tile;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.item.IReadItem;
import com.helpme.app.model.tile.edge.IEdge;

import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-04-21.
 */
public interface IReadTile {
    List<Maybe<IReadItem>> readItems();
    Maybe<IReadItem> readItem(int index);
    Map<Vector2f,IEdge> readEdges();
    //IEdge readEdge(Vector2f direction);

}
