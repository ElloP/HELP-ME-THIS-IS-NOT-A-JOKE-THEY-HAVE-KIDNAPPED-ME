package com.helpme.app.model.tile.concrete;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;

import java.util.*;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Tile implements ITile {
    private Map<Vector2f, IEdge> edges;
    private List<Maybe<IItem>> items;

    public Tile(List<Maybe<IItem>> items, Map<Vector2f, IEdge> edges){
        this.items = items;
        this.edges = edges;
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
    public List<Maybe<IItem>> removeItems() {
        List<Maybe<IItem>> removed = items;
        items = new ArrayList<>();
        return removed;
    }

    @Override
    public Maybe<IItem> removeItem(int index) {
        Maybe<IItem> removed = items.get(index);
        items.set(index, new Nothing<>());
        return removed;
    }

    @Override
    public void addItem(IItem item){
        this.items.add(Maybe.wrap(item));
    }

    @Override
    public void addItems(IItem[] items){
        for(IItem item : items){
            if(item == null) continue;
            addItem(item);
        }
    }

    @Override
    public void addItems(List<Maybe<IItem>> items) {
        for(Maybe<IItem> maybeItem : items){
            maybeItem.run(item -> this.items.add(Maybe.wrap(item)));
        }
    }

    @Override
    public List<Maybe<IReadItem>> readItems() {
        List<Maybe<IReadItem>> reads = new ArrayList<>();
        for(Maybe<IItem> maybeItem : items){
            reads.add(Maybe.wrap(maybeItem));
        }
        return reads;
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return Maybe.wrap(items.get(index));
    }

    @Override
    public Map<Vector2f, IEdge> readEdges() {
        return edges;
    }

    //@Override
    //public IEdge readEdge(Vector2f direction) {
    //    return edges.get(direction);
    //}

}