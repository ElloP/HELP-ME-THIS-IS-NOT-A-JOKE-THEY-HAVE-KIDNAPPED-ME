package com.helpme.app.bodytest;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IReadItem;

/**
 * Created by kopa on 2017-05-12.
 */
public class MockInventory implements IInventory {
    private IItem activeItem;
    private IItem defaultItem;

    public MockInventory(IItem activeItem, IItem defaultItem){
        this.activeItem = activeItem;
        this.defaultItem = defaultItem;
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
    public Maybe<IReadItem[]> readItems() {
        return null;
    }

    @Override
    public Maybe<IReadItem[]> readKeychain() {
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
    public void setItems(Maybe<IItem[]> items) {

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
    public IItem[] dropItems() {
        return new IItem[0];
    }

    @Override
    public void changeActiveItem(int itemIndex) {

    }
}
