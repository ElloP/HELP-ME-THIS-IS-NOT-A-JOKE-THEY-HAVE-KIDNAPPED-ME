package com.helpme.app.tile;

import com.helpme.app.item.IItem;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

import java.util.List;

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
