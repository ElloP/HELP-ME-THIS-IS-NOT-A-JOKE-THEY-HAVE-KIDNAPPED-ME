package com.helpme.app.inventorytest;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.body.inventory.concrete.InventoryFactory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.body.concrete.visitor.Pickup;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by kopa on 2017-05-12.
 */
public class InventoryTest {
    private IInventory inventory;

    @Before
    public void setup() {
        inventory = InventoryFactory.createInventory(new IItem[]{MockItem.item(), null, MockItem.item(), null}, MockItem.defaultItem(), null);
    }

    @Test
    public void testPickupItem() {
        IItem mockItem = MockItem.pickup();
        inventory.addItem(mockItem);
        assert (inventory.getItem(1).equals(mockItem));
    }

    @Test
    public void testDropItem() {
        Maybe<IItem> maybeItem = inventory.dropItem(0);
        assert (inventory.getItem(0) instanceof Nothing && maybeItem.check(i -> i.equals(MockItem.item())));
    }

    @Test
    public void testDropItems() {
        List<Maybe<IItem>> items = inventory.dropItems();
        assert (inventory.readItem(0) instanceof Nothing
                && inventory.readItem(1) instanceof Nothing
                && inventory.readItem(2) instanceof Nothing
                && inventory.readItem(3) instanceof Nothing
                && items.get(0).check(i -> i.equals(MockItem.item()))
                && items.get(1) instanceof Nothing
                && items.get(2).check(i -> i.equals(MockItem.item()))
                && items.get(3) instanceof Nothing
        );
    }

    @Test
    public void testPickupKey() {
        MockItem.key1().accept(new Pickup(inventory));
        MockItem.key2().accept(new Pickup(inventory));
        MockItem.key3().accept(new Pickup(inventory));
        assert (inventory.hasKey(MockItem.key1()) && inventory.hasKey(MockItem.key2()) && inventory.hasKey(MockItem.key3()));
    }
}