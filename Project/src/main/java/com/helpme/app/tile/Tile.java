package com.helpme.app.tile;

import com.helpme.app.item.IItem;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.utils.Clone;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.interfaces.ICloneable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Tile implements ITile {
    private IEdge[] edges = new IEdge[4];
    private List<IItem> items;

    public Tile(IItem[] items){
        this.items = items == null ? new ArrayList<>() : Clone.toList(items);
    }


    @Override
    public IEdge getEdge(Vector2f direction) {
        try {
            return edges[Vector2fToIndex(direction)];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            return null;
        }
    }


    @Override
    public void setEdge(IEdge edge, Vector2f direction) {
        try {
            edges[Vector2fToIndex(direction)] = edge;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }

    @Override
    public IItem[] removeItems() {
        IItem[] removed = items.toArray(new IItem[items.size()]);
        items.clear();
        return removed;
    }

    @Override
    public IItem removeItem(int index) {
        IItem removed = items.get(index);
        items.set(index, null);
        return removed;
    }

    @Override
    public void addItem(IItem item){
        this.items.add(item);
    }

    @Override
    public void addItems(IItem[] items){
        this.items.addAll(Arrays.asList(items));
    }


    private int Vector2fToIndex(Vector2f vec0) {
        if (vec0.equals(Vector2f.up)) {
            return 0;
        }
        if (vec0.equals(Vector2f.right)) {
            return 1;
        }
        if (vec0.equals(Vector2f.down)) {
            return 2;
        }
        if (vec0.equals(Vector2f.left)) {
            return 3;
        }

        return -1;
    }
}
