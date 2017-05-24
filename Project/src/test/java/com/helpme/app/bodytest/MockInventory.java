package com.helpme.app.bodytest;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.body.inventory.IInventory;
import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.IReadItem;

import java.util.List;

/**
 * Created by kopa on 2017-05-12.
 */
public class MockInventory implements IInventory {
    private IItem activeItem;
    private IItem defaultItem;
    List<Maybe<IItem>> items;

    boolean itemAdded;
    int copy;
    int setActiveItem;

    public MockInventory(IItem activeItem, IItem defaultItem){
        this.activeItem = activeItem;
        this.defaultItem = defaultItem;
    }


    @Override
    public boolean hasKey(IItem key) {
        return false;
    }

    @Override
    public IInventory copy() {
        copy++;
        return this;
    }

    @Override
    public int readSize() {
        return 0;
    }

    @Override
    public int readActiveItemIndex() {
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
        return false;
    }

    @Override
    public Maybe<IItem> getItem(int index) {
        return null;
    }

    @Override
    public Maybe<IItem> getActiveItem() {
        return Maybe.wrap(activeItem);
    }

    @Override
    public Maybe<IItem> getDefaultItem() {
        return Maybe.wrap(defaultItem);
    }

    @Override
    public void setItems(List<Maybe<IItem>> items) {
        this.items = items;
    }

    @Override
    public boolean addItem(IItem item) {
        return itemAdded;
    }

    @Override
    public boolean deleteItem(IItem item) {
        return false;
    }

    @Override
    public Maybe<IItem> dropItem(int index) {
        return items.get(index);
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
        return items;
    }

    @Override
    public void setActiveItem(int itemIndex) {
        setActiveItem = itemIndex;
    }
}
