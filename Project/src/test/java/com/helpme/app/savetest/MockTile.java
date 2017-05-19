package com.helpme.app.savetest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.world.tile.edge.IEdge;

import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-15.
 */
public class MockTile implements ITile{
    List<Maybe<IItem>> items;
    Map<Vector2f, IEdge> edges;

    public MockTile(List<Maybe<IItem>> items, Map<Vector2f, IEdge> edges){
        this.items = items;
        this.edges = edges;
    }

    @Override
    public Maybe<IEdge> getEdge(Vector2f direction) {
        return null;
    }

    @Override
    public void setEdge(IEdge edge, Vector2f direction) {

    }

    @Override
    public List<Maybe<IItem>> removeItems() {
        return null;
    }

    @Override
    public Maybe<IItem> removeItem(int index) {
        return null;
    }

    @Override
    public void addItem(IItem item) {

    }

    @Override
    public void addItems(IItem[] items) {

    }

    @Override
    public void addItems(List<Maybe<IItem>> items) {

    }

    @Override
    public List<Maybe<IReadItem>> readItems() {
        return Maybe.cast(items);
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return null;
    }

    @Override
    public Map<Vector2f, IEdge> readEdges() {
        return edges;
    }
}