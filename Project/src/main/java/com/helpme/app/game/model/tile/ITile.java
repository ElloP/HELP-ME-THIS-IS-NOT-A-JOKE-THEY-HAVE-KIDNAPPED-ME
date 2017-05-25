package com.helpme.app.game.model.tile;

import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;

import java.util.List;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface ITile extends IReadTile{
    Maybe<IEdge> getEdge(Vector2f direction);
    void setEdge(Vector2f direction, IEdge edge);
    List<Maybe<IItem>> removeItems();
    Maybe<IItem> removeItem(int index);
    void addItem(IItem item);
    void addItems(IItem[] items);
    void addItems(List<Maybe<IItem>> items);
}
