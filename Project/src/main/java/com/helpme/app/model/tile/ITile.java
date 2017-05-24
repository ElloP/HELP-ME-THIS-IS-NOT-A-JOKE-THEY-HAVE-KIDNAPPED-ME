package com.helpme.app.model.tile;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

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
