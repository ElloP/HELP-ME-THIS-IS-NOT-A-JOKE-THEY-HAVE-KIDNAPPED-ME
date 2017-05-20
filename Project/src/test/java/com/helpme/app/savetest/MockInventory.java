package com.helpme.app.savetest;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;

import java.util.List;

/**
 * Created by kopa on 2017-05-15.
 */
public class MockInventory implements IInventory {
    List<Maybe<IItem>> mockItems;
    List<Maybe<IItem>> mockKeys;

    public MockInventory(List<Maybe<IItem>> mockItems, List<Maybe<IItem>> mockKeys){
        this.mockItems = mockItems;
        this.mockKeys = mockKeys;
    }

    @Override
    public boolean hasKey(IItem key) {
        return false;
    }

    @Override
    public IInventory clone() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public List<Maybe<IReadItem>> readItems() {
        return Maybe.cast(mockItems);
    }

    @Override
    public List<Maybe<IReadItem>> readKeychain() {
        return Maybe.cast(mockKeys);
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return null;
    }

    @Override
    public boolean hasItem(IItem item) {
        return false;
    }

    @Override
    public Maybe<IItem> getItem(int index) {
        return null;
    }

    @Override
    public Maybe<IItem> getActiveItem() {
        return null;
    }

    @Override
    public Maybe<IItem> getDefaultItem() {
        return null;
    }

    @Override
    public void setItems(List<Maybe<IItem>> items) {

    }

    @Override
    public boolean addItem(IItem item) {
        return false;
    }

    @Override
    public boolean deleteItem(IItem item) {
        return false;
    }

    @Override
    public Maybe<IItem> dropItem(int index) {
        return null;
    }

    @Override
    public boolean addStack(IItem item, int amount) {
        return false;
    }

    @Override
    public void addKey(IItem key) {

    }

    @Override
    public List<Maybe<IItem>> dropItems() {
        return null;
    }

    @Override
    public void changeActiveItem(int itemIndex) {

    }
}
