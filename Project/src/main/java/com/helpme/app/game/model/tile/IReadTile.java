package com.helpme.app.game.model.tile;

import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;

import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-04-21.
 *
 * Interface for reading a ITiles information
 *
 */
public interface IReadTile {
    List<Maybe<IReadItem>> readItems();
    Maybe<IReadItem> readItem(int index);
    Map<Vector2f,IEdge> readEdges();
}
