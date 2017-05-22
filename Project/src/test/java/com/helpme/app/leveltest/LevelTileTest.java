package com.helpme.app.leveltest;

import com.helpme.app.model.body.IBody;
import com.helpme.app.model.body.IReadBody;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.level.ILevel;
import com.helpme.app.model.level.concrete.Level;
import com.helpme.app.model.tile.ITile;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-23.
 */
public class LevelTileTest {
    private ILevel level;
    private MockBody mockBody;
    private MockTile mockTile;

    @Before
    public void setup() {
        Map<Vector2f, ITile> tiles = new HashMap<>();
        List<IBody> bodies = new ArrayList<>();
        mockBody = new MockBody();
        mockTile = new MockTile();

        tiles.put(Vector2f.ZERO, new MockTile());
        tiles.put(Vector2f.NORTH, new MockTile());
        tiles.put(Vector2f.EAST, mockTile);

        bodies.add(mockBody);

        level = new Level(null, Vector2f.ZERO, tiles, bodies);
    }

    @Test
    public void testTileValid() {
        assert (level.isTileValid(Vector2f.NORTH));
    }

    @Test
    public void testTileInvalid() {
        assert (!level.isTileValid(Vector2f.WEST));
    }

    @Test
    public void testTileOccupied() {
        assert (level.isTileOccupied(Vector2f.NORTH));
    }

    @Test
    public void testTileVacant() {
        assert (!level.isTileOccupied(new Vector2f(1, 0)));
        mockBody.dead = true;
        assert (!level.isTileOccupied(new Vector2f(0, 1)));
    }

    @Test
    public void testAddTileItems() {
        List<Maybe<IItem>> mockItems = new ArrayList<>();
        MockItem mockItem0 = new MockItem("mockItem0");
        MockItem mockItem1 = new MockItem("mockItem1");
        mockItems.add(new Just<>(mockItem0));
        mockItems.add(new Just<>(mockItem1));

        level.addTileItems(Vector2f.ZERO, mockItems);
        List<Maybe<IItem>> tileItems = level.getTiles().get(Vector2f.ZERO).removeItems();

        assert (tileItems.size() == 2 &&
                tileItems.contains(new Just<>(mockItem0)) &&
                tileItems.contains(new Just<>(mockItem1)));
    }

    @Test
    public void testAddTileItemsInvalidTile(){
        List<Maybe<IItem>> mockItems = new ArrayList<>();
        MockItem mockItem = new MockItem("mockItem");
        mockItems.add(new Just<>(mockItem));

        assert (!level.addTileItems(new Vector2f(8,8), mockItems));
    }

    @Test
    public void testAddTileItemsInvalidItemList(){
        List<Maybe<IItem>> mockItems = null;
        assert (!level.addTileItems(Vector2f.ZERO, mockItems));
    }

    @Test
    public void testAddTileItem() {
        MockItem mockItem = new MockItem("mockItem0");

        level.addTileItem(Vector2f.ZERO, mockItem);
        List<Maybe<IItem>> tileItems = level.getTiles().get(Vector2f.ZERO).removeItems();

        assert (tileItems.size() == 1 &&
                tileItems.contains(new Just<>(mockItem)));
    }

    @Test
    public void testAddTileItemInvalidTile() {
        MockItem mockItem = new MockItem("mockItem0");
        assert (!level.addTileItem(new Vector2f(8, 8), mockItem));
    }

    @Test
    public void testAddTileItemInvalidItem() {
        MockItem mockItem = null;
        assert (!level.addTileItem(Vector2f.ZERO, mockItem));
    }

    @Test
    public void testRemoveTileItems() {
        List<Maybe<IItem>> mockItems = new ArrayList<>();
        IItem mockItem0 = new MockItem("mockItem0");
        IItem mockItem1 = new MockItem("mockItem1");
        mockItems.add(new Just<>(mockItem0));
        mockItems.add(new Just<>(mockItem1));
        mockTile.items = mockItems;

        Maybe<List<Maybe<IItem>>> tileItems = level.removeTileItems(Vector2f.EAST);
        assert (tileItems.check(items -> items.contains(new Just<>(mockItem0)) && items.contains(new Just<>(mockItem1))));
    }

    @Test
    public void testRemoveTileItem() {
        IItem mockItem = new MockItem("mockItem");
        mockTile.item = mockItem;

        Maybe<IItem> tileItem = level.removeTileItem(Vector2f.EAST, 0);
        assert (tileItem.check(item -> item == mockItem));
    }


    @Test
    public void testUpdateTile() {
        List<Maybe<IItem>> mockItems = new ArrayList<>();
        MockItem mockItem0 = new MockItem("mockItem0");
        MockItem mockItem1 = new MockItem("mockItem1");
        MockInventory mockInventory;

        mockItems.add(new Just<>(mockItem0));
        mockItems.add(new Just<>(mockItem1));

        mockInventory = new MockInventory(mockItems);

        mockBody.dead = true;
        mockBody.mockInventory = mockInventory;

        level.updateTile(mockBody.readPosition());
        ITile tile = level.getTiles().get(mockBody.readPosition());
        List<Maybe<IItem>> items = tile.removeItems();
        List<IReadBody> bodies = level.readBodies();

        assert (items.size() == 2 &&
                items.contains(new Just<>(mockItem0)) &&
                items.contains(new Just<>(mockItem1)) &&
                bodies.size() == 0 &&
                !bodies.contains(mockBody));
    }
}
