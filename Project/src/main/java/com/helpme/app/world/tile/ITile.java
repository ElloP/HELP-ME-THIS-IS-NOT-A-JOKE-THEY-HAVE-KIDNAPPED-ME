package com.helpme.app.world.tile;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface ITile {
    IEdge getEdge(Vector2f direction);
    void setEdge(IEdge edge, Vector2f direction);
    IItem[] removeItems();
    IItem removeItem(int index);
    void addItem(IItem item);
    void addItems(IItem[] item);
}
