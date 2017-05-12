package com.helpme.app.inventorytest;

import com.helpme.app.bodytest.MockInventory;
import com.helpme.app.bodytest.MockTarget;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.Body;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.inventory.Inventory;
import com.helpme.app.world.character.target.ITarget;
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
        inventory = new Inventory(new IItem[]{MockItem.item(), null, null, null}, MockItem.def(), null);
    }

    @Test
    public void testPickup(){
        IItem mockItem = MockItem.pickup();
        inventory.addItem(mockItem);
        assert (inventory.getItem(1).check(i -> i.equals(mockItem)));
    }


}