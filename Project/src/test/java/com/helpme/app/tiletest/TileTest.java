package com.helpme.app.tiletest;

import com.helpme.app.model.tile.concrete.Tile;
import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;
import com.helpme.app.model.tile.ITile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-13.
 */
public class TileTest {
    private ITile tile;

    public void setup() {
        List<Maybe<IItem>> items = new ArrayList<>();
        Map<Vector2f, IEdge> edges = new HashMap<>();

        items.add(new Just<>(new MockItem("item0")));
        items.add(new Just<>(new MockItem("item1")));
        items.add(new Just<>(new MockItem("item2")));

        edges.put(Vector2f.NORTH, new MockEdge());
        edges.put(Vector2f.EAST, new MockEdge());
        edges.put(Vector2f.SOUTH, new MockEdge());
        edges.put(Vector2f.WEST, new MockEdge());

        tile = new Tile(items, edges);
    }

    @Test
    public void testAddItem() {
        tile.addItem(new MockItem("pickup"));
        List<Maybe<IReadItem>> items = tile.readItems();
        assert (items.size() == 4
                && items.get(0).getValue().readName().equals("item0")
                && items.get(1).getValue().readName().equals("item1")
                && items.get(2).getValue().readName().equals("item2")
                && items.get(3).getValue().readName().equals("pickup")
        );
    }

    @Test
    public void testAddItems() {
        tile.addItems(new IItem[]{new MockItem("pickup0"), new MockItem("pickup1")});
        List<Maybe<IReadItem>> items = tile.readItems();
        assert (items.size() == 5
                && items.get(0).getValue().readName().equals("item0")
                && items.get(1).getValue().readName().equals("item1")
                && items.get(2).getValue().readName().equals("item2")
                && items.get(3).getValue().readName().equals("pickup0")
                && items.get(4).getValue().readName().equals("pickup1")
        );
    }

    @Test
    public void testRemoveItem() {
        tile.removeItem(1);
        List<Maybe<IReadItem>> items = tile.readItems();
        assert (items.size() == 3
                && items.get(0).getValue().readName().equals("item0")
                && items.get(1) instanceof Nothing
                && items.get(2).getValue().readName().equals("item2")
        );
    }

    @Test
    public void testRemoveItems() {
        tile.removeItems();
        List<Maybe<IReadItem>> items = tile.readItems();
        assert (items.size() == 0);
    }

}
