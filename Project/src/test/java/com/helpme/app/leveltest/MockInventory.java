package com.helpme.app.leveltest;

import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.IReadItem;
import com.helpme.app.utils.maybe.Maybe;

import java.util.List;

/**
 * Created by kopa on 2017-05-22.
 */
public class MockInventory implements IInventory {
    private List<Maybe<IItem>> mockItems;

    MockInventory(List<Maybe<IItem>> mockItems){
        this.mockItems = mockItems;
    }

    @Override
    public boolean hasKey(IItem key) {
        return false;
    }

    @Override
    public IInventory copy() {
        return null;
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
    public Maybe<IReadItem> readDefaultItem() {
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
        return mockItems;
    }

    @Override
    public void setActiveItem(int itemIndex) {

    }
}
