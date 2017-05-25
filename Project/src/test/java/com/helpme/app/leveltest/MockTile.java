package com.helpme.app.leveltest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.model.tile.edge.IEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-15.
 */
public class MockTile implements ITile {
    List<Maybe<IItem>> items = new ArrayList<>();
    IItem item;

    @Override
    public Maybe<IEdge> getEdge(Vector2f direction) {
        return new Just(new MockEdge());
    }

    @Override
    public void setEdge(Vector2f direction, IEdge edge) {

    }

    @Override
    public List<Maybe<IItem>> removeItems() {
        return items;
    }

    @Override
    public Maybe<IItem> removeItem(int index) {
        return Maybe.wrap(item);
    }

    @Override
    public void addItem(IItem item) {
        items.add(new Just<>(item));
    }

    @Override
    public void addItems(IItem[] items) {

    }

    @Override
    public void addItems(List<Maybe<IItem>> items) {
        this.items = items;
    }

    @Override
    public List<Maybe<IReadItem>> readItems() {
        return null;
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return null;
    }

    @Override
    public Map<Vector2f, IEdge> readEdges() {
        return null;
    }
}
