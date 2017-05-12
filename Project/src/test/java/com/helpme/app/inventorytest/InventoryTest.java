package com.helpme.app.inventorytest;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.InventoryFactory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.visitor.Pickup;
import org.junit.Before;
import org.junit.Test;

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
    public void testPickupItem(){
        IItem mockItem = MockItem.pickup();
        inventory.addItem(mockItem);
        assert (inventory.getItem(1).check(i -> i.equals(mockItem)));
    }

    @Test
    public void testDropItem(){
        Maybe<IItem> maybeItem = inventory.dropItem(0);
        assert (inventory.getItem(0) instanceof Nothing && maybeItem.check(i -> i.equals(MockItem.item())));
    }

    @Test
    public void testDropItems(){
        IItem[] items = inventory.dropItems();
        assert (inventory.getItem(0) instanceof Nothing
                && inventory.getItem(1) instanceof Nothing
                && inventory.getItem(2) instanceof Nothing
                && inventory.getItem(3) instanceof Nothing
                && items[0].equals(MockItem.item())
                && items[1] == null
                && items[2].equals(MockItem.item())
                && items[3] == null);
    }

    @Test
    public void testPickupKey(){
        MockItem.key1().accept(new Pickup(inventory));
        MockItem.key2().accept(new Pickup(inventory));
        MockItem.key3().accept(new Pickup(inventory));
        assert (inventory.hasKey(MockItem.key1()) && inventory.hasKey(MockItem.key2()) && inventory.hasKey(MockItem.key3()));
    }
}