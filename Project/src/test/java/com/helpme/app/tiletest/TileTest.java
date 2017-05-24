package com.helpme.app.tiletest;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.model.tile.concrete.Tile;
import com.helpme.app.model.tile.edge.IEdge;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import org.junit.Before;
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
    private MockEdge mockNorth;

    @Before
    public void setup() {
        List<Maybe<IItem>> items = new ArrayList<>();
        Map<Vector2f, IEdge> edges = new HashMap<>();

        items.add(new Just<>(new MockItem("item0")));
        items.add(new Just<>(new MockItem("item1")));
        items.add(new Just<>(new MockItem("item2")));

        mockNorth = new MockEdge();

        edges.put(Vector2f.NORTH, mockNorth);
        edges.put(Vector2f.EAST, new MockEdge());
        edges.put(Vector2f.SOUTH, new MockEdge());
        edges.put(Vector2f.WEST, new MockEdge());

        tile = new Tile(items, edges);
    }

    @Test
    public void testReadItems(){
        List<Maybe<IReadItem>> items = tile.readItems();
        assert (items.get(0).getValue().readName().equals("item0") &&
        items.get(1).getValue().readName().equals("item1") &&
        items.get(2).getValue().readName().equals("item2"));
    }

    @Test
    public void testGetEdge(){
        assert (tile.getEdge(Vector2f.NORTH).equals(new Just<>(mockNorth)));
    }

    @Test
    public void testSetEdge(){
        MockEdge newEdge = new MockEdge();
        tile.setEdge(Vector2f.NORTH, newEdge);
        assert (tile.getEdge(Vector2f.NORTH).equals(new Just<>(newEdge)));
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
    public void testAddItemsArray() {
        tile.addItems(new IItem[]{new MockItem("pickup0"), null, new MockItem("pickup1")});
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
    public void testAddItemsList() {
        List<Maybe<IItem>> pickups = new ArrayList<>();
        pickups.add(new Just<>(new MockItem("pickup0")));
        pickups.add(new Nothing<>());
        pickups.add(new Just<>(new MockItem("pickup1")));
        tile.addItems(pickups);
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
