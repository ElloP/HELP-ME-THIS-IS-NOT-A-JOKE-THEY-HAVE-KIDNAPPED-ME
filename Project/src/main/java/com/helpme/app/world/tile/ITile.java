package com.helpme.app.world.tile;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

import java.util.List;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface ITile extends IReadTile{
    Maybe<IEdge> getEdge(Vector2f direction);
    void setEdge(IEdge edge, Vector2f direction);
    List<Maybe<IItem>> removeItems();
    Maybe<IItem> removeItem(int index);
    void addItem(IItem item);
    void addItems(IItem[] items);
    void addItems(List<Maybe<IItem>> items);
}
