package com.helpme.app.itemtest.pickuptest;

import com.helpme.app.model.body.concrete.visitor.Pickup;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-20.
 */
public class PickupTest {
    private MockKey mockKey;
    private MockSingle mockSingle;
    private MockConsumable mockConsumable;
    private MockInventory mockInventory;


    @Before
    public void setup(){
        mockKey = new MockKey();
        mockSingle = new MockSingle();
        mockConsumable = new MockConsumable();
        mockInventory  = new MockInventory();
    }

    @Test
    public void testPickupKeyFullInventory(){
        mockInventory.fullInventory = true;
        assert (mockKey.accept(new Pickup(mockInventory)));
    }

    @Test
    public void testPickupKeyNotFullInventory(){
        assert (mockKey.accept(new Pickup(mockInventory)));
    }

    @Test
    public void testPickupSingleNotFullInventory(){
        assert (mockSingle.accept(new Pickup(mockInventory)));
    }

    @Test
    public void testPickupSingleFullInventory(){
        mockInventory.fullInventory = true;
        assert (!mockSingle.accept(new Pickup(mockInventory)));
    }

    @Test
    public void testPickupConsumableInventoryHasConsumable(){
        mockInventory.hasItem = true;
        mockInventory.fullInventory = true;
        assert (mockConsumable.accept(new Pickup(mockInventory)));
    }

    @Test
    public void testPickupConsumableInventoryNotFullInventory(){
        mockInventory.hasItem = false;
        mockInventory.fullInventory = false;
        assert (mockConsumable.accept(new Pickup(mockInventory)));
    }

    @Test
    public void testPickupConsumableFullInventory(){
        mockInventory.hasItem = false;
        mockInventory.fullInventory = true;
        assert (!mockConsumable.accept(new Pickup(mockInventory)));
    }

    @Test
    public void testPickupNoConsumable(){
        mockConsumable.stacks = 0;
        assert (!mockConsumable.accept(new Pickup(mockInventory)));
    }
}
