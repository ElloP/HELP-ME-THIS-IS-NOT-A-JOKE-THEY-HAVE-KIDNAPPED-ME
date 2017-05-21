package com.helpme.app.pickuptest;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.body.inventory.IInventory;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;

import java.util.List;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockInventory implements IInventory {
    boolean fullInventory;
    boolean hasItem;

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
        return null;
    }

    @Override
    public List<Maybe<IReadItem>> readKeychain() {
        return null;
    }

    @Override
    public Maybe<IReadItem> readItem(int index) {
        return null;
    }

    @Override
    public boolean hasItem(IItem item) {
        return hasItem;
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
        if (fullInventory) {
            return false;
        }
        return true;
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
        return hasItem;
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
