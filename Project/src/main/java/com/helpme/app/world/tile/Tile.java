package com.helpme.app.world.tile;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.edge.EdgeType;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.utils.Clone;
import com.helpme.app.utils.Vector2f;

import java.util.*;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Tile implements ITile {
    private Map<Vector2f, IEdge> edges;
    private List<IItem> items;

    public Tile(IItem[] items){
        this(items, null);
    }

    public Tile(IItem[] items, Map<Vector2f, IEdge> edges){
        this.items = items == null ? new ArrayList<>() : Clone.toList(items);
        this.edges = edges == null ? new HashMap<>() : edges;
    }


    @Override
    public Maybe<IEdge> getEdge(Vector2f direction) {
        return Maybe.wrap(edges.get(direction));
    }


    @Override
    public void setEdge(IEdge edge, Vector2f direction) {
        edges.put(direction, edge);
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
        for(IItem item : items){
            if(item == null) continue;
            addItem(item);
        }
    }

    @Override
    public Maybe<IReadItem[]> readItems() {
        return Maybe.wrap(items.toArray(new IItem[items.size()]));
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return Maybe.wrap(items.get(index));
    }

    @Override
    public Map<Vector2f, IEdge> readEdges() {
        return edges;
    }

    @Override
    public IEdge readEdge(Vector2f direction) {
        return edges.get(direction);
    }
}
