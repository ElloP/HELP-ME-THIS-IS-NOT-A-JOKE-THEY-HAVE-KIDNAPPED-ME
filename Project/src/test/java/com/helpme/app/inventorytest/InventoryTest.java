package com.helpme.app.inventorytest;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.body.inventory.concrete.InventoryFactory;
import com.helpme.app.world.item.IItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by kopa on 2017-05-12.
 */
public class InventoryTest {
    private IInventory inventory;
    private MockSingle mockSingle0;
    private MockSingle mockSingle1;
    private MockSingle mockSingle2;

    @Before
    public void setup() {
        mockSingle0 = new MockSingle("Item");
        mockSingle1 = new MockSingle("Item");
        mockSingle2 = new MockSingle("DefaultItem");
        inventory = InventoryFactory.createInventory(new IItem[]{mockSingle0, null, mockSingle1, null}, mockSingle2, null);
    }

    @Test
    public void testPickupItem() {
        IItem mockPickup = new MockSingle("Pickup");
        inventory.addItem(mockPickup);
        assert (inventory.getItem(1).equals(mockPickup));
    }

    @Test
    public void testDropItem() {
        Maybe<IItem> maybeItem = inventory.dropItem(0);
        assert (inventory.getItem(0) instanceof Nothing && maybeItem.check(i -> i.readName().equals(mockSingle0.readName())));
    }

    @Test
    public void testDropItems() {
        List<Maybe<IItem>> items = inventory.dropItems();
        assert (inventory.readItem(0) instanceof Nothing
                && inventory.readItem(1) instanceof Nothing
                && inventory.readItem(2) instanceof Nothing
                && inventory.readItem(3) instanceof Nothing
                && items.get(0).check(i -> i.readName().equals(mockSingle0.readName()))
                && items.get(1) instanceof Nothing
                && items.get(2).check(i -> i.readName().equals(mockSingle1.readName()))
                && items.get(3) instanceof Nothing
        );
    }
}