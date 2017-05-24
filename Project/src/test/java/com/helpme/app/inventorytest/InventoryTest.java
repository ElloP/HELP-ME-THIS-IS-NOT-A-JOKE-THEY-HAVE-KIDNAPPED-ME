package com.helpme.app.inventorytest;

import com.helpme.app.model.body.inventory.concrete.Inventory;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.model.body.inventory.IInventory;
import com.helpme.app.model.item.IItem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-05-12.
 */
public class InventoryTest {
    private IInventory inventory;
    private MockConsumable mockConsumable0;
    private MockSingle mockSingle0;
    private MockSingle mockSingle1;
    private MockSingle mockSingle2;
    private MockKey mockKey0;
    private MockKey mockKey1;

    @Before
    public void setup() {
        mockConsumable0 = new MockConsumable("Consumable0");
        mockSingle0 = new MockSingle("Single0");
        mockSingle1 = new MockSingle("Single1");
        mockSingle2 = new MockSingle("DefaultItem");
        mockKey0 = new MockKey("Key0");
        mockKey1 = new MockKey("Key1");

        List<Maybe<IItem>> items = new ArrayList<>();
        List<Maybe<IItem>> keychain = new ArrayList<>();

        items.add(new Just<>(mockSingle0));
        items.add(new Nothing<>());
        items.add(new Just<>(mockSingle1));
        items.add(new Nothing<>());

        keychain.add(new Just<>(mockKey0));
        keychain.add(new Just<>(mockKey1));

        inventory = new Inventory(items, mockSingle2, keychain);
    }

    @Test
    public void testAddItem() {
        IItem mockPickup = new MockSingle("Pickup");
        assert (inventory.addItem(mockPickup) && inventory.getItem(1).check(item -> item.equals(mockPickup)));
    }


    @Test
    public void testAddItemFull() {
        IItem mockPickup0 = new MockSingle("Pickup0");
        IItem mockPickup1 = new MockSingle("Pickup1");
        IItem mockPickup2 = new MockSingle("Pickup2");

        assert (inventory.addItem(mockPickup0) &&
                inventory.addItem(mockPickup1) &&
                !inventory.addItem(mockPickup2) &&
                inventory.hasItem(mockPickup0) &&
                inventory.hasItem(mockPickup1) &&
                !inventory.hasItem(mockPickup2));
    }

    @Test
    public void testAddkey() {
        IItem mockPickup = new MockKey("PickupKey");
        inventory.addKey(mockPickup);
        assert (inventory.hasKey(mockPickup));
    }

    @Test
    public void testAddStack() {
        MockConsumable consumable = new MockConsumable("Consumable0");
        inventory.addItem(mockConsumable0);
        assert (inventory.addStack(consumable, 2) &&
                mockConsumable0.stacks == 2 &&
                consumable.stacks == 0);
    }

    @Test
    public void testAddStackNoMatch() {
        assert (!inventory.addStack(mockConsumable0, 0));
    }

    @Test
    public void testAddStackNotConsumable() {
        MockSingle single = new MockSingle("MockSingle0");
        assert (!inventory.addStack(single, 2));
    }

    @Test
    public void testDeleteItemSuccess(){
        assert (inventory.deleteItem(mockSingle1) && inventory.getItem(2).isNothing());
    }

    @Test
    public void testDeleteItemFailure(){
        assert (!inventory.deleteItem(mockConsumable0));
    }

    @Test
    public void testDropItem() {
        Maybe<IItem> maybeItem = inventory.dropItem(0);
        assert (inventory.getItem(0).isNothing() && maybeItem.check(i -> i.readName().equals(mockSingle0.readName())));
    }

    @Test
    public void testDropItems() {
        List<Maybe<IItem>> items = inventory.dropItems();
        assert (inventory.readItem(0).isNothing() &&
                inventory.readItem(1).isNothing() &&
                inventory.readItem(2).isNothing() &&
                inventory.readItem(3).isNothing() &&
                items.get(0).check(i -> i.readName().equals(mockSingle0.readName())) &&
                items.get(1).isNothing() &&
                items.get(2).check(i -> i.readName().equals(mockSingle1.readName())) &&
                items.get(3).isNothing()
        );
    }

    @Test
    public void testSetItems() {
        inventory.setItems(new ArrayList<>());
        assert (inventory.readItems().isEmpty());
    }


    @Test
    public void testHasKey() {
        assert (inventory.hasKey(mockKey0) &&
                inventory.hasKey(mockKey1));
    }

    @Test
    public void testHasNotKey() {
        assert (!inventory.hasKey(new MockKey("other")));
    }

    @Test
    public void testHasItem() {
        assert (inventory.hasItem(mockSingle0) &&
                inventory.hasItem(mockSingle1));
    }

    @Test
    public void testGetDefaultItem() {
        assert (inventory.getDefaultItem().check(item -> item.equals(mockSingle2)));
    }

    @Test
    public void testReadActiveItemIndex() {
        assert (inventory.readActiveItemIndex() == 0);

        inventory.setActiveItem(2);
        assert (inventory.readActiveItemIndex() == 2);
    }

    @Test
    public void testSetActiveItemLowerBound() {
        inventory.setActiveItem(-2);
        assert (inventory.readActiveItemIndex() == 2);
    }

    @Test
    public void testSetActiveItemUpperBound() {
        inventory.setActiveItem(5);
        assert (inventory.readActiveItemIndex() == 1);
    }

    @Test
    public void testGetActiveItem() {
        Maybe<IItem> maybeActive = inventory.getActiveItem();
        assert (maybeActive.check(item -> item.equals(mockSingle0)));

        inventory.setActiveItem(1);
        maybeActive = inventory.getActiveItem();
        assert (maybeActive.isNothing());

        inventory.setActiveItem(2);
        maybeActive = inventory.getActiveItem();
        assert (maybeActive.check(item -> item.equals(mockSingle1)));
    }

    @Test
    public void testGetActiveItemNoSize() {
        inventory.setItems(new ArrayList<>());
        Maybe<IItem> maybeActive = inventory.getActiveItem();
        assert (maybeActive.isNothing());
    }
}