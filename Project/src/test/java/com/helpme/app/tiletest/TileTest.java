package com.helpme.app.tiletest;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;
import com.helpme.app.world.tile.ITile;
import com.helpme.app.world.tile.TileFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by kopa on 2017-05-13.
 */
public class TileTest {
    private ITile tile;

    @Before
    public void setup() {
        tile = TileFactory.createTile(new IItem[]{new MockItem("item0"), new MockItem("item1"), new MockItem("item2")}, new MockEdge(), new MockEdge(), new MockEdge(), new MockEdge());
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
