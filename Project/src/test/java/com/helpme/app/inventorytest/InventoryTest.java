package com.helpme.app.inventorytest;

import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.item.IItem;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-12.
 */
public class InventoryTest {
    private IInventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory(new IItem[]{MockItem.item(), null, MockItem.item(), null}, MockItem.def(), null);
    }

    @Test
    public void testPickupItem(){
        IItem mockItem = MockItem.pickup();
        inventory.addItem(mockItem);
        assert (inventory.getItem(1).check(i -> i.equals(mockItem)));
    }

    @Test
    public void testDropItem(){
        inventory.dropItem(0);
        assert (inventory.getItem(0) instanceof Nothing);
    }

    @Test
    public void testDropItems(){
        inventory.dropItems();
        assert (inventory.getItem(0) instanceof Nothing
                && inventory.getItem(1) instanceof Nothing
                && inventory.getItem(2) instanceof Nothing
                && inventory.getItem(3) instanceof Nothing);
    }


}